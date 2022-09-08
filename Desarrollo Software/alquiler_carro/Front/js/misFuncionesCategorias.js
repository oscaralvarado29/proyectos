function traerInformacionCategorias() {
    console.log("test");
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Category/all",
        url: "http://localhost:8080/api/Category/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarCategoria(respuesta);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se consulto correctamente");
        }
    });
}

function pintarCategoria(respuesta) {

    console.log("se ngresa a pintar");
    let myTable = "<table>";
    myTable += "<th>Nombre</th>";
    myTable += "<th>Descripcion</th>";
    myTable += "<th>Cantidad de vehiculos</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].name + "</td>";
        myTable += "<td>" + respuesta[i].description + "</td>";
        myTable += "<td>" + respuesta[i].vehicle.length + "</td>";
        myTable += '<td><button  onclick="cargarDatosCategorias(' + respuesta[i].idCategory + ')">Lanzar</button></td>';
        myTable += "<td> <button onclick=' actualizarInformacionCategorias(" + respuesta[i].idCategory + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='borrarCategoria(" + respuesta[i].idCategory + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarCategorias").html(myTable);
}

function cargarDatosCategorias(id) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Category/" + id,
        url: "http://localhost:8080/api/Category/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#Cname").val(item.name);
            $("#Cdescription").val(item.description);
        },
    });
}

function agregarCategorias() {
    
    if ($("#Cdescription").val().length == 0 || $("#Cname").val().length == 0 ) {

        alert("Todos los campos son obligatorios");
    } else {

        console.log("name: " + $("#Cname").val().length + " description: " + $("#Cdescription").val().length);
        let var2 = {
            name: $("#Cname").val(),
            description: $("#Cdescription").val()
        };
        console.log(var2);
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(var2),
            //url: "http://10.0.1.5:8080/api/Category/save",
            url: "http://localhost:8080/api/Category/save",


            success: function (response) {
                console.log(response);
                console.log("Se guardo correctamente");
                alert("Se guardo correctamente");
            },

            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se guardo correctamente");


            }
        });
    }

}

function actualizarInformacionCategorias(idElemento) {
    $(document).ready(function(){     
        $('#modal2').modal('show');
        $('#modal2').draggable({}); //arrastrable 
       
    });   
    if ($("#Cname").val().length === 0 || $("#Cdescription").val().length === 0) {

        alert("Todos los campos son obligatorios");
    } else {


        let myData = {
            idCategory: idElemento,
            name: $("#Cname").val(),
            description: $("#Cdescription").val()

        };
        console.log(myData);
        let dataToSend = JSON.stringify(myData);
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Category/update",
            url: "http://localhost:8080/api/Category/update",
            type: "PUT",
            data: dataToSend,
            contentType: "application/JSON",
            datatype: "JSON",
            success: function (respuesta) {
                $("#resultado").empty();
                $("#id").val("");
                $("#Cname").val("");
                $("#Cdescription").val("");
                traerInformacionCategorias();
                alert("se ha Actualizado correctamente la categoria")
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se Actualizo Correctamente!")
            }
        });
    }

}

function borrarCategoria(idElemento) {
    let myData = {
        idCategory: idElemento
    };
    let dataToSend = JSON.stringify(myData);
    console.log(dataToSend);
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Category/" + idElemento,
        url: "http://localhost:8080/api/Category/delete" ,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            traerInformacionCategorias();
            alert("Se ha eliminado correctamente")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se Elimino Correctamente!")
        }
    });

}