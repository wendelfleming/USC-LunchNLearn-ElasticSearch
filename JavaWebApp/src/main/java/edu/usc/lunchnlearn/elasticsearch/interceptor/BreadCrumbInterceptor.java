/**
 * Copyright 2015 University of Southern California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usc.lunchnlearn.elasticsearch.interceptor;

import edu.usc.lunchnlearn.elasticsearch.bean.Crumb;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AOP interceptor used to handle breadcrumbs.  This handles putting breadcrumbs on all of the pages.  It
 * separates this functionality from the controllers so that they do not even know there are breadcrumbs.
 *
 * @author wendel fleming
 *
 */
public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {

    private static final String BREADCRUMBS = "BREADCRUMBS";
    private static final String BREADCRUMBS_URLS = "BREADCRUMBSURLS";
    private Map<String, String> uriDisplayTextMapping;
    private List<String> uriExclusionList;

    public BreadCrumbInterceptor() {
        uriExclusionList = new ArrayList<>();
        uriDisplayTextMapping = new HashMap<>();
    }

    /**
     * This method is called after every controller has finished processing a request.  This sets up
     * the breadcrumbs that will be displayed on the resulting view page.
     * @param request           {@link javax.servlet.http.HttpServletRequest}
     * @param response          {@link javax.servlet.http.HttpServletResponse}
     * @param handler           Handler object
     * @param modelAndView      {@link org.springframework.web.servlet.ModelAndView}
     * @throws Exception        Throws Exception.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        //do not intercept REST calls
        if(!"application/json".equals(response.getContentType()) && !isExclusion(request)) {
            HttpSession httpSession = request.getSession(true);
            boolean firstTime = false;
            if(httpSession.getAttribute(BREADCRUMBS) == null) {
                httpSession.setAttribute(BREADCRUMBS, new HashMap<Long,Crumb>());
                firstTime = true;
            }
            if(httpSession.getAttribute(BREADCRUMBS_URLS) == null) {
                httpSession.setAttribute(BREADCRUMBS_URLS, new HashMap<String,Long>());
            }

            @SuppressWarnings("unchecked")
            Map<Long, Crumb> breadcrumbs = (Map<Long,Crumb>)httpSession.getAttribute(BREADCRUMBS);
            @SuppressWarnings("unchecked")
            Map<String, Long> breadcrumbUrls = (Map<String, Long>)httpSession.getAttribute(BREADCRUMBS_URLS);

            Crumb crumb = getCurrentCrumb(request, breadcrumbUrls, breadcrumbs, modelAndView);

            //if first page in session is not the index, plant an index crumb
            if(firstTime && !request.getRequestURL().toString().contains("index")) {
                Crumb indexCrumb = createIndexCrumb(crumb, request);
                breadcrumbs.put(indexCrumb.getX(), indexCrumb);
                breadcrumbUrls.put(indexCrumb.getUrl(), indexCrumb.getX());

                crumb.setParentX(indexCrumb.getX());
            }

            //put this URL into the list so that we don't recreate this page's breadcrumb
            breadcrumbs.put(crumb.getX(), crumb);
            breadcrumbUrls.put(crumb.getUrl(), crumb.getX());

            if(modelAndView.getModelMap().containsKey("urlParams")) {
                Map urlParams = (Map)modelAndView.getModelMap().get("urlParams");
                urlParams.put("x", crumb.getX());
            }

            modelAndView.getModelMap().put("x", crumb.getX());
            modelAndView.getModelMap().put("bCrumbs", breadcrumbs);

        }
    }


    private boolean isExclusion(HttpServletRequest request) {
        boolean exclusion = false;

        StringBuffer stringBuffer = request.getRequestURL();
        if(request.getQueryString() != null) {
            stringBuffer.append("?").append(request.getQueryString());
        }
        String url = stringBuffer.toString();

        for(String uri : this.uriExclusionList) {
            if(url.contains(uri)) {
                exclusion = true;
                break;
            }
        }
        return exclusion;
    }

    private Crumb getCurrentCrumb(HttpServletRequest request, Map<String, Long> breadcrumbUrls, Map<Long, Crumb> breadcrumbs, ModelAndView modelAndView) {
        Crumb crumb = new Crumb();

        StringBuffer stringBuffer = request.getRequestURL();
        if(request.getQueryString() != null) {
            stringBuffer.append("?").append(request.getQueryString());
        }
        crumb.setUrl(stringBuffer.toString());

        //if the exact URL has already been requested, use that crumb instead of creating a new one.
        if(breadcrumbUrls.containsKey(crumb.getUrl())) {
            return breadcrumbs.get(breadcrumbUrls.get(crumb.getUrl()));
        }

        //set the display text of the breadcrumb
        crumb.setDisplayText("");

        if(modelAndView.getModelMap().containsAttribute("title")) {
            crumb.setDisplayText((String)modelAndView.getModelMap().get("title"));
        }
        else {
            for(String uri : uriDisplayTextMapping.keySet()) {
                if(crumb.getUrl().contains(uri)) {
                    crumb.setDisplayText(uriDisplayTextMapping.get(uri));
                    break;
                }
            }
        }

        //set the parent value
        if(request.getParameterMap().containsKey("x")) {
            if(StringUtils.isNumeric(request.getParameter("x"))) {
                crumb.setParentX(Long.parseLong(request.getParameter("x")));
            }
        }

        //set the time in millis for X
        crumb.setX(System.currentTimeMillis());

        return crumb;
    }


    //If this is the first breadcrumb but not the index, put an index breadcrumb in as its parent.
    private Crumb createIndexCrumb(Crumb firstCrumb, HttpServletRequest request) {
        Crumb indexCrumb = new Crumb();
        StringBuffer sb = new StringBuffer();

        if(request.getScheme().equals("http") && request.getHeader("x-forwarded-proto") != null) {
            sb.append("https://");
        }
        else {
            sb.append(request.getScheme()).append("://");
        }

        sb.append(request.getServerName());

        if(request.getServerPort() != 80 && request.getServerPort() != 443) {
            sb.append(":").append(request.getServerPort());
        }

        sb.append(request.getContextPath());
        sb.append(request.getServletPath());
        sb.append("/index.html");

        indexCrumb.setUrl(sb.toString());
        indexCrumb.setDisplayText("Index");
        indexCrumb.setX(firstCrumb.getX() - 100L);

        return indexCrumb;
    }


    public void setUriDisplayTextMapping(Map<String, String> uriDisplayTextMapping) {
        this.uriDisplayTextMapping = uriDisplayTextMapping;
    }

    public void setUriExclusionList(List<String> uriExclusionList) {
        this.uriExclusionList = uriExclusionList;
    }
}




