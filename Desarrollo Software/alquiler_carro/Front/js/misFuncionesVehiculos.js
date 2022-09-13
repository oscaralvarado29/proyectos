function autoInicioCategoria() {
    console.log("se esta ejecutando")
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Category/all",
        url:"http://localhost:8080/api/Category/all",
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
function traerInformacionVehiculo() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Vehicle/all",
        url: "http://localhost:8080/api/Vehicle/all",
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
    myTable += "<th>Tipo de vehiculo</th>";
    myTable += "<th>Nombre</th>";
    myTable += "<th>Color</th>";
    myTable += "<th>Marca</th>";
    myTable += "<th>Modelo</th>";
    myTable += "<th>Caballos de fuerza</th>";
    myTable += "<th>Cilindros del motor</th>";
    myTable += "<th>Puestos del vehiculo</th>";
    myTable += "<th>Mensajes</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    "</tr>";

    for (i = 0; i < response.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + response[i].category.name + "</td>";
        myTable += "<td>" + response[i].name + "</td>";
        myTable += "<td>" + response[i].color + "</td>";
        myTable += "<td>" + response[i].brand + "</td>";
        myTable += "<td>" + response[i].model + "</td>";
        myTable += "<td>" + response[i].horsePower + "</td>";
        myTable += "<td>" + response[i].engineCylinders + "</td>";
        myTable += "<td>" + response[i].seating + "</td>";
        if (typeof response[i].messages.messageText === 'undefined'){
            myTable += "<td>" + "Ninguno" + "</td>";
        } else {
            myTable += "<td>" + response[i].messages.messageText + "</td>";
        }
        myTable += '<td><button onclick="cargarDatosVehiculo(' + response[i].idVehicle + ')">Lanzar</button></td>';
        myTable += '<td><button onclick="actualizarVehiculo(' + response[i].idVehicle + ')">Actualizar</button></td>';
        myTable += '<td><button onclick="borrarVehiculo(' + response[i].idVehicle + ')">Borrar</button></td>';
        myTable += "</tr>";
    }
    myTable += "</table>";
    $('#mostrarVehiculos').html(myTable);
}
//Capturar informacion para Actualizar
function cargarDatosVehiculo(id) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Vehicle/" + id,
        url: "http://localhost:8080/api/Vehicle/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#name").val(item.name);
            $("#color").val(item.color);
            $("#brand").val(item.brand);
            $("#model").val(item.model);
            $("#horsePower").val(item.horsePower);
            $("#engineCylinders").val(item.engineCylinders);
            $("#seating").val(item.seating);
        },

        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se obtuvieron los datos ")
        }
    });
}

function agregarVehiculo() {

    if ( $("#name").val()== "" || $("#color").val()== "" || $("#brand").val() == "" || $("#model").val()== "" || $("#horsePower").val()== "" || $("#engineCylinders").val()== "" || $("#seating").val()== "") {
        alert("Todos los campos son obligatorios");        
    } else {

        let elemento = {
            name: $("#name").val(),
            color: $("#color").val(),
            brand: $("#brand").val(),
            model: $("#model").val(),
            horsePower: $("#horsePower").val(),
            engineCylinders: $("#engineCylinders").val(),
            seating: $("#seating").val(),
            category: {
                idCategory: +$("#select-category").val()
            },
        }
        let dataToSend = JSON.stringify(elemento);
        console.log(elemento.category.idCategory);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            //url: "http://10.0.1.5:8080/api/Vehicle/save",
            url: "http://localhost:8080/api/Vehicle/save",
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
                $("#horsePower").val("");
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
function borrarVehiculo(idElemento) {
    var elemento = {
        idVehicle: idElemento
    }
    var dataToSend = JSON.stringify(elemento);
    console.log(dataToSend);
    $.ajax({
        dataType: 'json',
        data: dataToSend,
        //url: "http://10.0.1.5:8080/api/Vehicle/delete" ,
        url: "http://localhost:8080/api/Vehicle/delete" ,
        type: 'DELETE',
        contentType: "application/JSON",
        success: function (response) {
            console.log(response);
            $("#mostrarMaquinas").empty();

            alert("se ha Eliminado Correctamente!")
            //Limpiar Campos
            $("#mostrarVehiculos").empty();
            $("#name").val("");
            $("#color").val("");
            $("#brand").val("");
            $("#model").val("");
            $("#horsePower").val("");
            $("#engineCylinders").val("");
            $("#seating").val("");
            traerInformacionVehiculo()
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se Elimino Correctamente!")
        }
    });
}

//Manejador PUT
function actualizarVehiculo(idElemento) {

    if (  $("#name").val()== "" || $("#color").val()== "" || $("#brand").val() == "" || $("#model").val()== "" || $("#horsePower").val()== "" || $("#engineCylinders").val()== "" || $("#seating").val()== "") {
        alert("Todos los campos son obligatorios")
    } else {
        let elemento = {
            idVehicle: idElemento,
            name: $("#name").val(),
            color: $("#color").val(),
            brand: $("#brand").val(),
            model: $("#model").val(),
            horsePower: $("#horsePower").val(),
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
            //url: "http://10.0.1.5:8080/api/Vehicle/update",
            url: "http://localhost:8080/api/Vehicle/update",
            type: "PUT",

            success: function (response) {
                console.log(response);
                $("#miListaCar").empty();
                traerInformacionVehiculo();
                alert("se ha Actualizado Correctamente!")
                //Limpiar Campos
                $("#name").val(" ");
                $("#color").val(" ");
                $("#brand").val(" ");
                $("#model").val(" ");
                $("#horsePower").val(" ");
                $("#engineCylinders").val(" ");
                $("#seating").val(" ");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se Actualizo Correctamente!")
            }
        });
    }
}