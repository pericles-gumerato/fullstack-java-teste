'use strict';

angular.module('contabilizeiApp.calcular_impostos', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/calcular_impostos', {
            templateUrl: 'calcular_impostos/calcular_impostos.html',
            controller: 'CalcularImpostosCtrl'
        });
    }])

    .controller('CalcularImpostosCtrl', ['$scope', '$http', 'BACKEND_SERVER_ADDRESS', function ($scope, $http, backendAddress) {
        var dataObj = {
            'maxPorPagina': 1000
        };
        var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/consulta/clientes', dataObj);

        res.success(function (data, status, headers, config) {
            $scope.clientes = data.result.clientes;
        });
        res.error(function (data, status, headers, config) {
            console.error('ERROR');
            console.error(data);
            alert('failure message: ' + JSON.stringify({data: data}));
        });

        $scope.calcularImpostos = function (clienteId, mes, ano) {
            if (clienteId == undefined || mes == undefined || ano == undefined) {
                $scope.mensagemErro = 'Por favor selecione todos os valores para a operação.';
                return;
            }
            var dataObj = {
                'clienteId': clienteId,
                'mes': mes,
                'ano': ano
            };
            var res = $http.post(backendAddress.url + ':' + backendAddress.port + '/calculo/impostos_mes', dataObj);

            res.success(function (data, status, headers, config) {
                if(!data.result.sucesso) {
                    $scope.mensagemErro = data.result.mensagem; // TODO This SHOULD be mapped with an error code, but I'm doing this because of the short time.
                    return;
                }
                $scope.mensagemStatus = 'Cálculo realizado com sucesso';
            });
            res.error(function (data, status, headers, config) {
                $scope.mensagemStatus = 'Erro ao realizar a ação.';
            });
        };
    }]);