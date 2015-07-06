/**
 * Copyright 2015 wendel fleming
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


$(document).ready(function() {
    $("#wow_search").autocomplete({
        source: function( request, response ) {

            var urlForm = page_ajaxmapping + request.term;

            $.ajax({
                type: "GET",
                url: urlForm,
                context: this
            }).done(function(data) {
                response(data);
            }).fail(function( jqXHR, textStatus ) {
                alert( "Request failed: Error code = " + jqXHR.status);
            });
        }
    });

    $("#wow_search").on("autocompleteselect", function( event, ui ) {
        $("#wow_search").val("\"" + ui.item.value + "\"");
        event.preventDefault();
    } );

})