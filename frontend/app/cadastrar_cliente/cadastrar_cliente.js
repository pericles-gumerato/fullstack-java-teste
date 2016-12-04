'use strict';

angular.module('contabilizeiApp.cadastrar_cliente', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/cadastrar_cliente', {
            templateUrl: 'cadastrar_cliente/cadastrar_cliente.html',
            controller: 'CadastrarClienteCtrl'
        });
    }])

    .controller('CadastrarClienteCtrl', ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        $scope.anexos = {};
        $scope.cadastrarCliente = function () {

            var formData = $scope.cadastroClienteForm;

            if (isNaN(Number(formData.cnpj.$modelValue))) {
                $scope.erroCnpj = 'O cnpj somente pode conter números.'
                return;
            }

            var anexosEnvio = [];
            for (var i = 0; i < 3; i++) {
                if ($scope.anexos[i] == true) {
                    anexosEnvio.push(i);
                }
            }

            var dataObj = {
                'razaoSocial': formData.razaoSocial.$modelValue,
                'cnpj': formData.cnpj.$modelValue,
                'regimeTributario': formData.regimeTributario.$modelValue,
                'anexos': anexosEnvio
            };
            var res = $http.put(backendAddress.url + ':' + backendAddress.port + '/cadastro/cliente', dataObj);

            res.success(function (data, status, headers, config) {
                $scope.mensagemStatus = 'Cliente cadastrado';
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemStatus = 'Erro ao realizar a ação.';
            });
        };
    }]);