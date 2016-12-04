'use strict';

angular.module('contabilizeiApp.buscar_impostos', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/buscar_impostos', {
            templateUrl: 'buscar_impostos/buscar_impostos.html',
            controller: 'BuscarImpostosCtrl'
        });
    }])

    .controller('BuscarImpostosCtrl', ['$scope', '$http', '$sce', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, $sce, backendAddress) {
        var dataObj = {
            'maxPorPagina': 1000
        };

        $scope.impostos = [];
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes = data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            $scope.mensagemErro = $sce.trustAsHtml('Erro ao recuperar clientes!');
        });

        $scope.buscarImpostos = function (clienteId, mes, ano) {
            // Clean messages
            $scope.mensagemErro = $sce.trustAsHtml('');
            $scope.mensagemStatus = $sce.trustAsHtml('');

            if (clienteId == undefined || mes == undefined || ano == undefined) {
                $scope.mensagemErro = 'Por favor selecione todos os valores para a busca.';
                return;
            }
            var dataObj = {
                'clienteId': clienteId,
                'mes': mes,
                'ano': ano
            };
            var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/impostos_mes', dataObj);

            res.success(function (data, status, headers, config) {
                $scope.impostos = data.result.impostos;
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemErro = $sce.trustAsHtml('Erro ao recuperar impostos!');
            });
        };

        $scope.marcarImpostoComoPago = function (clienteId, imposto) {
            if (clienteId == undefined || imposto == undefined) {
                $scope.mensagemErro = $sce.trustAsHtml('Erro ao marcar imposto como pago!');
                return;
            }
            var dataObj = {
                'clienteId': clienteId,
                'impostoId': imposto.id
            };
            var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/update/marcar_imposto_como_pago', dataObj);

            res.success(function (data, status, headers, config) {
                imposto.pago = true;
                $scope.mensagemStatus = $sce.trustAsHtml('Operação realizada com sucesso');
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemStatus = $sce.trustAsHtml('Erro ao realizar a ação.');
            });
        };
    }]);