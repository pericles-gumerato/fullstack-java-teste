'use strict';

angular.module('contabilizeiApp.buscar_notas', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/buscar_notas', {
            templateUrl: 'buscar_notas/buscar_notas.html',
            controller: 'BuscarNotasCtrl'
        });
    }])

    .controller('BuscarNotasCtrl', ['$scope', '$http', '$sce', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, $sce, backendAddress) {
        var dataObj = {
            'maxPorPagina': 1000
        };

        $scope.notas = [];
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes = data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            $scope.mensagemErro = $sce.trustAsHtml('Erro ao recuperar clientes!');
        });

        $scope.buscarNotas = function (clienteId, mes, ano) {
            // Clean messages
            $scope.mensagemErro = $sce.trustAsHtml('');
            $scope.mensagemStatus = $sce.trustAsHtml('');

            if (clienteId == undefined || mes == undefined || ano == undefined) {
                $scope.mensagemErro = $sce.trustAsHtml('Por favor selecione todos os valores para a busca.');
                return;
            }
            var dataObj = {
                'clienteId': clienteId,
                'mes': mes,
                'ano': ano
            };
            var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/notas_fiscais', dataObj);

            res.success(function (data, status, headers, config) {
                $scope.notas = data.result.notas;
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemStatus = $sce.trustAsHtml('Erro ao realizar a ação.');
            });
        };
    }]);