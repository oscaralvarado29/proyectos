function getCategory() {

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Category/all",
        url: "http://localhost:8080/api/Category/all",
        type: "GET",
        datatype: "JSON",
        success: function (category) {
            drawCategory(category);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function drawCategory(category) {

    let myTable = "<table>";
    myTable += "<th>Nombre</th>";
    myTable += "<th>Descripción</th>";
    myTable += "<th>Cantidad de vehiculos</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "</tr>";
    for (const element of category) {
        myTable += "<tr>";
        myTable += "<td>" + element.name + "</td>";
        myTable += "<td>" + element.description + "</td>";
        myTable += "<td>" + element.vehicle.length + "</td>";
        myTable += '<td><button  onclick="launchDataCategory(' + element.idCategory + ')">Lanzar</button></td>';
        myTable += "<td> <button onclick=' putCategory(" + element.idCategory + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='deleteCategory(" + element.idCategory + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarCategorias").html(myTable);
}

function launchDataCategory(categoryId) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Category/" + categoryId,
        url: "http://localhost:8080/api/Category/" + categoryId,
        type: 'GET',

        success: function (response) {
            $("#name").val(response.name);
            $("#description").val(response.description);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function postCategory() {
    
    if ($("#description").val() === "" || $("#name").val() === "" ) {

        alert("Todos los campos son obligatorios");
    } else {
        let categoryData = {
            name: $("#name").val(),
            description: $("#description").val()
        };
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(categoryData),
            //url: "http://10.0.1.5:8080/api/Category/save",
            url: "http://localhost:8080/api/Category/save",


            success: function (response) {
                $("#name").val("");
                $("#description").val("");
                alert("La categoria "+response.name+" se ha guardado correctamente");
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
    }

}

function putCategory(idElemento) {

    if ($("#name").val() === "" || $("#description").val() === "") {

        alert("Todos los campos son obligatorios");
    } else {

        let categoryData = {
            idCategory: idElemento,
            name: $("#name").val(),
            description: $("#description").val()

        };
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Category/update",
            url: "http://localhost:8080/api/Category/update",
            type: "PUT",
            data: JSON.stringify(categoryData),
            contentType: "application/JSON",
            datatype: "JSON",
            success: function (respuesta) {
                $("#mostrarCategorias").empty();
                $("#name").val("");
                $("#description").val("");
                getCategory();
                alert("se ha Actualizado correctamente la categoria "+respuesta.name)
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
    }

}

function deleteCategory(idElemento) {
    let categoryData = {
        idCategory: idElemento
    };
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Category/" + idElemento,
        url: "http://localhost:8080/api/Category/delete" ,
        type: "DELETE",
        data: JSON.stringify(categoryData),
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {
            $("#mostrarCategorias").empty();
            getCategory();
            alert("Se ha eliminado correctamente la categoria ")
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });

}
