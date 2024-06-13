<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product list</title>
    <style>
        table, tr, td {
            border: black solid;
            border-collapse: collapse;
            padding: 5px;
        }
    </style>
</head>
<body>
<h1 style="color: blue">Список продуктов</h1>
<h2>Добрый день!</h2>
<p>Список продуктов:</p>
<table>
    <tr>
        <td>Наименование</td>
        <td>Калории</td>
    </tr>

<#list request?keys as key>
    <tr>
        <td>${key}</td>
        <td>${request[key]}</td>
    </tr>
</#list>

</table>

</body>
</html>