'use strict';

app.controller('AdminController',
    function ($scope, $mdDialog, userService, $location, $anchorScroll, pageSize) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
        ////////////LOAD USERS////////////
        $scope.params = {
            page: 0,
            size: pageSize
        };

        $scope.loadUsers = function () {
            userService.getAllUsers($scope.params).then(
                function (response) {
                    console.log(response.data);
                    $scope.users = response.data;
                },
                function (error) {
                    console.log(error);
                }
            );
        };
        $scope.loadUsers();

        $scope.pageBtnClicked = function (pageNumber) {
            $scope.params.page = pageNumber;
            $scope.loadUsers();
            $location.hash('bannerr');
            $anchorScroll();
        };

        $scope.checkPrev = function (isFirstPage, pageNumber) {
            if (!isFirstPage) {
                $scope.pageBtnClicked(pageNumber - 1);
            }
        };

        $scope.checkNext = function (isLastPage, pageNumber) {
            if (!isLastPage) {
                $scope.pageBtnClicked(pageNumber + 1);
            }
        };

        ////////////DELETE USER////////////
        $scope.showConfirm = function(ev, user) {
            // Appending dialog to document.body to cover sidenav in docs app
            let confirm = $mdDialog.confirm()
                .title('Would you like to delete user: ' + user.email + '?')
                .textContent('Selected User will set to inactive and without permission to login in the system!')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Set to Inactive!')
                .cancel('Cancel');

            $mdDialog.show(confirm).then(function() {
                $scope.deleteUser(user.id);
            }, function() {
                $scope.status = 'You decided to keep your debt.';
            });
        };
        
        $scope.deleteUser = function (id) {
            userService.deleteUser(id).then(
                function (response) {
                    console.log(response.data);
                    $scope.loadUsers();
                },
                function (error) {
                    console.log(error);
                }
            );
        };

        /////////////EDIT USER//////////////
        $scope.customFullscreen = false;

        $scope.showAdvanced = function (ev, user) {
            $mdDialog.show({
                locals:{dataToPass: user},
                controller: PeshoController,
                templateUrl: 'templates/partials/userDialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            })
                .then(function (answer) {
                    userService.editUser(answer).then(
                        function (response) {
                            $scope.loadUsers();
                        },
                        function (error) {
                            console.log(error);
                        }
                    );
                }, function () {
                    console.log('cancel clicked!');
                });
        };

        function PeshoController($scope, $mdDialog, dataToPass) {
            $scope.user = dataToPass;
            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.answer = function (answer) {
                $mdDialog.hide(answer);
            };
        }
    }
);