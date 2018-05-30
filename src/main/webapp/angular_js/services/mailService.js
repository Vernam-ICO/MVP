'use strict';

app.factory('mailService',
    function ($http, baseServiceUrl) {
        return {
            sendComment: function (comment) {
                let request = {
                    method: 'POST',
                    url: baseServiceUrl + '/receive-comment',
                    data: comment
                };
                return $http(request);
            }
        }
    }
);
