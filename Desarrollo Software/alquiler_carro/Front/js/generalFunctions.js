function generalFunctions (jqXHR, exception) {
    let msg = '';
    if (jqXHR.status === 0) {
        msg += 'Not connect.\n Verify Network.';
    } else if (jqXHR.status === 404) {
        msg += 'Requested page not found. [404]';
    } else if (jqXHR.status === 500) {
        msg += 'Internal Server Error [500].';
    } else if (exception === 'parsererror') {
        msg += 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg += 'Time out error.';
    } else if (exception === 'abort') {
        msg += 'Ajax request aborted.';
    } else {
        msg += 'Uncaught Error.\n' + jqXHR.responseText;
    }
    return(msg);
}

function  formatDate (date,version) {
    let day = date.getDate() + 1;
    let month = (date.getMonth() + 1);
    let year = date.getFullYear();
    let formatted_date;
    let dayString;
    let monthString;
    if (day <10){
        dayString = "0" + day.toString()
    }else{
        dayString = day.toString()
    }
    if (month <10){
        monthString = "0" + month.toString()
    }else{
        monthString = month.toString()
    }

    if (    version === 1) {
        formatted_date = dayString +"-" + monthString +"-" +year;
    }    else {
        formatted_date = year +"-" + monthString +"-" + dayString;

    }
    return formatted_date;
}

function getDataClientAndVehicle() {
    getDataClients();
    getDataVehicle();
}
function  getDataClients() {

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/all",
        url: "http://localhost:8080/api/Client/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-client");
            $.each(respuesta, function (_id, name) {
                $select.append('<option value=' + name.idClient + '>' + name.name + '</option>');
            });
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }

    })
}

function getDataVehicle() {

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Vehicle/all",
        url: "http://localhost:8080/api/Vehicle/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-vehicle");
            $.each(respuesta, function (_id, name) {
                $select.append('<option value=' + name.idVehicle + '>' + name.name + '</option>');

            });
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }

    })
}