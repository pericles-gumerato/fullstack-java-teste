'use strict';

angular.module('contabilizeiApp.buscar_notas', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/buscar_notas', {
            templateUrl: 'buscar_notas/buscar_notas.html',
            controller: 'BuscarNotasCtrl'
        });
    }])

    .controller("BuscarNotasCtrl", ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        var dataObj = {
            "maxPorPagina" : 1000
        };
        $scope.notas = [];
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes=data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            console.error('ERROR');
            console.error(data);
            alert("failure message: " + JSON.stringify({data: data}));
        });

        $scope.buscarNotas = function(clienteId, mes, ano) {
            if(clienteId == undefined || mes == undefined || ano == undefined) {
                $scope.mensagemErro = 'Por favor selecione todos os valores para a busca.';
                return;
            }
            var dataObj = {
                "clienteId" : clienteId,
                "mes" : mes,
                "ano" : ano
            };
            var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/notas_fiscais', dataObj);

            res.success(function (data, status, headers, config) {
                $scope.notas=data.result.notas;
            });
            res.error(function (data, status, headers, config) {
                console.error('ERROR');
                console.error(data);
                alert("failure message: " + JSON.stringify({data: data}));
            });
        };
    }]);