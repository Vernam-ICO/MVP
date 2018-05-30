'use strict';

app.controller('PurchaseHistoryController',
    function ($scope, $window, $sce, purchaseHistoryService, userService) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');

        $scope.user = {};

        userService.getUser().then(function (response) {
                $scope.user = response.data
            },
            function (errors) {
                console.log(errors)
            });

        purchaseHistoryService.getAll()
            .then(function (response) {
                console.log(response.data);
                $scope.data = response.data;
            }, function (error) {
                console.log('ERROR IN PURCHASE HISTORY CONTROLLER -GET ALL')
            });

        $scope.viewPolicy = function (policyId) {
            $scope.showModal = true;
            purchaseHistoryService.getFile(policyId)
                .then(function (response) {
                        let file = new Blob([(response.data)], {type: 'application/pdf'});
                        console.log(file);
                        let fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);

                        $('#myModal').modal('toggle');
                        let frame = $('<iframe>').attr("src", $scope.content);
                        frame.css("width", "768px");
                        frame.css("height", "500px");
                        frame.attr("frameborder", 0);

                        $('#modal-goes-here').append(frame);
                    },
                    function (errors) {
                        console.log(errors)
                    });
        };

        $("#myModal").on("hidden.bs.modal", function () {
            $('#modal-goes-here').empty();
        });
    }
);