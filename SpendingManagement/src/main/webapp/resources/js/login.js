/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let check = document.getElementById("customCheck");
let username = document.getElementById("username");
let password = document.getElementById("password");

if (localStorage.getItem("remembered")) {
    check.checked = true;
    username.value = localStorage.getItem("username");
    password.value = localStorage.getItem("password");
}

check.addEventListener("click", function () {
    if (check.checked) {
        localStorage.setItem("remembered", true);
        localStorage.setItem("username", username.value);
        localStorage.setItem("password", password.value);
    } else {
        localStorage.setItem("remembered");
        localStorage.removeItem("username");
        localStorage.removeItem("password");
    }
});

//if ($('#customCheck').is(':checked')) {
//    localStorage.setItem('username', $('#username').val());
//    localStorage.setItem('password', $('#password').val());
//} else {
//    localStorage.removeItem('username');
//    localStorage.removeItem('password');
//}
//
//$(document).ready(function () {
//    var name = localStorage.setItem("username");
//    var pass = localStorage.setItem("password");
//    if (name && pass) {
//        $('#username').val(name);
//        $('#password').val(pass);
//        $('#customCheck').prop('checked', true);
//    }
//});

