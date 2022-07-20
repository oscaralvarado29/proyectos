function autoInicioCategoria() {
    console.log("se esta ejecutando")
    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Category/all",
        //url:"http://localhost:8080/api/Category/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            let $select = $("#select-category");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.idCategory + '>' + name.name + '</option>');
                console.log("select " + name.idCategory);
            });
        }

    })
}
//Manejador GET
function traerInformacionVehucilo() {
    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Vehicle/all",
        //url: "http://localhost:8080/api/Vehicle/all",
        type: "GET",
        datatype: "JSON",
        success: function (response) {
            console.log(response);
            pintarVehiculo(response);
        }

    });

}

function pintarVehiculo(response) {

    let myTable = "<table>"
    myTable += "<tr>";
    myTable += "<td>Tipo de vehiculo</td>";
    myTable += "<td>Nombre</td>";
    myTable += "<td>Color</td>";
    myTable += "<td>Marca</td>";
    myTable += "<td>Modelo</td>";
    myTable += "<td>Caballos de fuerza</td>";
    myTable += "<td>Cilindros del motor</td>";
    myTable += "<td>Puestos del vehiculo</td>";
    myTable += "<td>Mensajes</td>";
    "</tr>";

    for (i = 0; i < response.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + response[i].category.name + "</td>";
        myTable += "<td>" + response[i].name + "</td>";
        myTable += "<td>" + response[i].color + "</td>";
        myTable += "<td>" + response[i].brand + "</td>";
        myTable += "<td>" + response[i].model + "</td>";
        myTable += "<td>" + response[i].horsepower + "</td>";
        myTable += "<td>" + response[i].engineCylinders + "</td>";
        myTable += "<td>" + response[i].seating + "</td>";
        if (typeof response[i].messages.messageText === 'undefined'){
            myTable += "<td>" + "Ninguno" + "</td>";
        } else {
            myTable += "<td>" + response[i].messages.messageText + "</td>";
        }
        myTable += '<td><button class = "botonVehiculo" onclick="borrar(' + response[i].idVehicle + ')">Borrar</button></td>';
        myTable += '<td><button class = "botonVehiculo" onclick="cargarDatosVehiculo(' + response[i].idVehicle + ')">Lanzar</button></td>';
        myTable += '<td><button class = "botonVehiculo" onclick="actualizar(' + response[i].idVehicle + ')">Actualizar</button></td>';
        myTable += "</tr>";
    }
    myTable += "</table>";
    $('#mostrarVehiculos').html(myTable);
}
//Capturar informacion para Actualizar
function cargarDatosVehiculo(id) {
    $.ajax({
        dataType: 'json',
        url: "http:// 52.205.54.183:8080/api/Vehicle/" + id,
        //url: "http://localhost:8080/api/Vehicle/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#id").val(item.idVehicle);
            $("#name").val(item.name);
            $("#color").val(item.color);
            $("#brand").val(item.brand);
            $("#model").val(item.model);
            $("#horsepower").val(item.horsepower);
            $("#engineCylinders").val(item.engineCylinders);
            $("#seating").val(item.seating);
        },

        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se obtuvieron los datos ")
        }
    });
}

function agregarVehiculo() {

    if ( $("#color").val().length == 0 || $("#brand").val().length == 0 || $("#model").val().length == 0 || $("#horsepower").val().length == 0 || $("#engineCylinders").val().length == 0 || $("#seating").val().length == 0) {
        alert("Todos los campos son obligatorios")
    } else {

        let elemento = {
            name: $("#name").val(),
            color: $("#color").val(),
            brand: $("#brand").val(),
            model: $("#model").val(),
            horsepower: $("#horsepower").val(),
            engineCylinders: $("#engineCylinders").val(),
            seating: $("#seating").val(),
            category: {
                idCategory: +$("#select-category").val()
            },
        }
        let dataToSend = JSON.stringify(elemento);
        console.log(elemento);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http:// 52.205.54.183:8080/api/Vehicle/save",
            //url: "http://localhost:8080/api/Vehicle/save",
            data: dataToSend,
            datatype: 'json',

            success: function (response) {
                console.log(response);
                console.log("Se guardo Correctamente");
                //Limpiar Campos
                $("#name").val("");
                $("#color").val("");
                $("#brand").val("");
                $("#model").val("");
                $("#horsepower").val("");
                $("#engineCylinders").val("");
                $("#seating").val("");
                alert("Se ha guardado Correctamente!")
                window.location.reload()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se Guardo Correctamente")
            }
        });
    }
}
//Manejador DELETE
function borrar(idElemento) {
    var elemento = {
        idVehicle: idElemento
    }
    var dataToSend = JSON.stringify(elemento);
    console.log(dataToSend);
    $.ajax({
        dataType: 'json',
        data: dataToSend,
        url: "http:// 52.205.54.183:8080/api/Vehicle/delete" ,
        //url: "http://localhost:8080/api/Vehicle/delete" ,
        type: 'DELETE',
        contentType: "application/JSON",
        success: function (response) {
            console.log(response);
            $("#mostrarMaquinas").empty();

            alert("se ha Eliminado Correctamente!")
            //Limpiar Campos
            $("#resultado2").empty();
            $("#name").val("");
            $("#color").val("");
            $("#brand").val("");
            $("#model").val("");
            $("#horsepower").val("");
            $("#engineCylinders").val("");
            $("#seating").val("");
            traerInformacionVehucilo()
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se Elimino Correctamente!")
        }
    });
}

//Manejador PUT
function actualizar(idElemento) {

    if (  $("#name").val().length == 0 || $("#color").val().length == 0 || $("#brand").val().length == 0 || $("#model").val().length == 0 || $("#horsepower").val().length == 0 || $("#engineCylinders").val().length == 0 || $("#seating").val().length == 0) {
        alert("Todos los campos son obligatorios")
    } else {
        let elemento = {
            idVehicle: idElemento,
            name: $("#name").val(),
            color: $("#color").val(),
            brand: $("#brand").val(),
            model: $("#model").val(),
            horsepower: $("#horsepower").val(),
            engineCylinders: $("#engineCylinders").val(),
            seating: $("#seating").val(),
            category: {
                idCategory: +$("#select-category").val()
            },
        }
        //console.log(elemento);
        let dataToSend = JSON.stringify(elemento);

        $.ajax({
            datatype: 'json',
            data: dataToSend,
            contentType: "application/JSON",
            url: "http:// 52.205.54.183:8080/api/Vehicle/update",
            //url: "http://localhost:8080/api/Vehicle/update",
            type: "PUT",

            success: function (response) {
                console.log(response);
                $("#miListaCar").empty();
                traerInformacionVehucilo();
                alert("se ha Actualizado Correctamente!")
                //Limpiar Campos
                $("#name").val(" ");
                $("#color").val(" ");
                $("#brand").val(" ");
                $("#model").val(" ");
                $("#horsepower").val(" ");
                $("#engineCylinders").val(" ");
                $("#seating").val(" ");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se Actualizo Correctamente!")
            }
        });
    }
}