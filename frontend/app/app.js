'use strict';

// Declare app level module which depends on views, and components
angular.module('contabilizeiApp', [
    'ngRoute',
    'contabilizeiApp.buscar_impostos',
    'contabilizeiApp.buscar_notas',
    'contabilizeiApp.cadastrar_cliente',
    'contabilizeiApp.cadastrar_nota_fiscal',
    'contabilizeiApp.calcular_impostos'
]).constant("BACKEND_SERVER_ADDRESS", {
    "url": "http://localhost",
    "port": "8080"
}).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.otherwise({redirectTo: '/'});
}]);
