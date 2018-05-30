'use strict';

app.factory('pdfService',
    function ($http, baseServiceUrl, authService) {
        return {
            getPdf: function (data, success, error) {
                let request = {
                    method: 'POST',
                    responseType: 'arraybuffer',
                    url: baseServiceUrl + '/get-pdf',
                    data: data
                };
                return $http(request).then(success, error);
            }
        }
    }
);
