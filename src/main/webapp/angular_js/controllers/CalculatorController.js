'use strict';

app.controller('CalculatorController',
    function ($scope, $rootScope, $window, $anchorScroll, $sce,  paymentService, authService, userService, pdfService) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
        $scope.format = 'yyyy/MM/dd';
        $scope.myDate = new Date();
        $scope.data = {};
        $scope.data.paymentMethod = 'CREDIT_CARD';
        $scope.isOpen = false;

        if (authService.isLoggedIn()) {
            userService.getUser().then(
                function (response) {
                    $scope.data.user = response.data;
                },
                function (error) {
                    console.log(error);
                }
            );
        }
        $scope.thankYouMessage = 'Thank you for using our demo platform';

        $scope.requestPayment = function (data) {
            paymentService.create($scope.data)
                .then(function (response) {
                        console.log(response.data);
                        if (authService.isLoggedIn()){
                            $rootScope.thankYouMessage = 'Thank you for using our demo platform.At the provided email you will receive the newly created demo policy.';
                            $window.location.href = '#!/thank-you'
                        }else {
                            $rootScope.thankYouMessage = 'Thank you for using our demo platform. At the provided email you will receive a username and password to login to our system where you can get more information about the created policy.';
                            $window.location.href = '#!/thank-you'
                        }
                    },
                    function (errors) {
                        console.log(errors)
                    })
        };

        $scope.formatNumber = function (number) {
            return Math.round(number * 100) / 100;
        };

        $scope.setTokens = function (number) {
            $scope.data.tokens = Math.round((number * 0.3) / 0.18);
        };

        $scope.clickNext = function () {
            $anchorScroll();
        };

        $scope.viewPdf = function (data) {
            console.log(data);
            $scope.showModal = true;
            pdfService.getPdf(data).then(
                function (response) {
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
                function (error) {
                    console.log(error);
                }
            )
        };

        $("#myModal").on("hidden.bs.modal", function () {
            $('#modal-goes-here').empty();
        });
    }
);
