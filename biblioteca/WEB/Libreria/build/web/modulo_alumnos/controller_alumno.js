let usuarioss = [];
let indexAlumnoSeleccionado;

export function inicializar() {
    refrescarTabla();
    document.getElementById("btnDelete").classList.add("disabled");
}

export function refrescarTabla() {
    let filtro = document.getElementById("cmbFiltre").value;
    fetch("api/Busc/UVMPublicAll", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
            .then(response => response.json())
            .then(data => {
                if (data.exception != null) {
                    swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }
                if (data.errosec != null) {
                    Swal.fire('', data, errorsec, 'error');
                    window.location.replace('index.html');
                    return;
                }
                console.log(data);
                usuarioss = data;
                loadTable(data);
            });
}


export function buscarAl() {
    let filtro = document.getElementById("txtBusquedaAl").value;
    let datos = null;
    let params = null;

    datos = {
        nombre_usuario: filtro
    };

    params = new URLSearchParams(datos);

    fetch("api/Busc/UVMPublic", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => response.json())
            .then(data => {
                if (data.exception != null) {
                    swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }
                if (data.errosec != null) {
                    Swal.fire('', data, errorsec, 'error');
                    window.location.replace('index.html');
                    return;
                }
                console.log(data);
                loadTable2(data);
            });
}

function loadTable(data) {
    let cuerpo = "";

    data.forEach(function (usuario) {
        let registro =
                '<tr data-id="' + usuario.usrid + '" onclick="moduloAlumno.selectAlumno(' + usuario.usrid + ');">' +
                '<td>' + usuario.usrid + '</td><td>' + usuario.usrname + '</td></tr>';
        cuerpo += registro;
    });

    document.getElementById("tblAlumnos").innerHTML = cuerpo;
}

function loadTable2(data) {

    let registro =
            '<tr data-id="' + data.usrid + '" onclick="moduloAlumno.selectAlumno(' + data.usrid + ');">' +
            '<td>' + data.usrid + '</td><td>' + data.usrname + '</td></tr>';

    document.getElementById("tblAlumnos").innerHTML = registro;
}

function validarAlumno(alumno) {
    if (!alumno.nombre_usuario || !alumno.contrasenia) {
        Swal.fire('', 'Por favor, complete los campos obligatorios (Correo, contraseña).', 'error');
        return false;
    }
    return true;
}

export function save() {
    let datos = null;
    let params = null;

    let usuario = new Object();

    if (document.getElementById("id_usuario").value.trim().length < 1) {
        usuario.id_usuario = 0;
    } else {
        usuario.id_usuario = parseInt(document.getElementById("id_usuario").value);
    }

    usuario.telefono = document.getElementById("telefono").value;
    usuario.nombre_usuario = document.getElementById("nombre_usuario").value;
    usuario.contrasenia = document.getElementById("contrasenia").value;
    usuario.rfc = document.getElementById("rfc").value;

    if (!validarAlumno(usuario)) {
        return; // Detener la operación si la validación falla
    }

    datos = {
        datosUsu: JSON.stringify(usuario)
    };

    params = new URLSearchParams(datos);

    fetch("api/Usu/insert", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => response.json())
            .then(function (data) {
                if (data.exception != null) {
                    Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde.", 'error');
                    return;
                }

                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }

                if (data.errorperm != null) {
                    Swal.fire('', "No tiene permiso para realizar esta operación", 'warning');
                    return;
                }

                Swal.fire('', "Datos del alumno guardados correctamente", 'success');
                clean();
                refrescarTabla();
            });
}

export function update() {
    let datos = null;
    let params = null;
    let usuario = new Object();

    usuario.id_usuario = parseInt(document.getElementById("id_usuario").value);
    usuario.telefono = document.getElementById("telefono").value;
    usuario.nombre_usuario = document.getElementById("nombre_usuario").value;
    usuario.contrasenia = document.getElementById("contrasenia").value;
    usuario.rfc = document.getElementById("rfc").value;

    if (!validarAlumno(usuario)) {
        return; // Detener la operación si la validación falla
    }

    datos = {
        datosUsu: JSON.stringify(usuario)
    };

    params = new URLSearchParams(datos);

    fetch("api/Usu/update", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => response.json())
            .then(function (data) {
                if (data.exception != null) {
                    Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde.", 'error');
                    return;
                }

                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }

                if (data.errorperm != null) {
                    Swal.fire('', "No tiene permiso para realizar esta operación", 'warning');
                    return;
                }

                Swal.fire('', "Datos del alumno guardados correctamente", 'success');
                clean();
                refrescarTabla();
                document.getElementById("btnDelete").classList.add("disabled");
                document.getElementById("btnAdd").classList.remove("disabled");
            });
}


export function selectAlumno(id) {
    let nombre = null;

    for (let i = 0; i < usuarioss.length; i++) {
        if (usuarioss[i].usrid.toString() === id.toString()) {
            nombre = usuarioss[i];
            console.log(usuarioss[i].usrname.toString());
            break; // Salir del bucle una vez que se encuentra el elemento
        }
    }

    let datos = null;
    let params = null;
    datos = {
        nombre_usuario: nombre.usrname
    };

    params = new URLSearchParams(datos);

    fetch("api/Busc/UVM", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => response.json())
            .then(data => {
                if (data.exception != null) {
                    swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning');
                    return;
                }
                if (data.errosec != null) {
                    Swal.fire('', data, errorsec, 'error');
                    window.location.replace('index.html');
                    return;
                }
                console.log(data);
                document.getElementById("id_usuario").value = data.id_usuario;
                document.getElementById("telefono").value = data.telefono;
                document.getElementById("nombre_usuario").value = data.nombre_usuario;
                document.getElementById("contrasenia").value = data.contrasenia;
                document.getElementById("rfc").value = data.rfc;
                document.getElementById("btnAdd").classList.add("disabled");
                document.getElementById("btnDelete").classList.remove("disabled");

            });

}

export function clean() {
    document.getElementById("id_usuario").value = "";
    document.getElementById("telefono").value = "";
    document.getElementById("nombre_usuario").value = "";
    document.getElementById("contrasenia").value = "";
    document.getElementById("rfc").value = "";
    document.getElementById("btnDelete").classList.add("disabled");
    document.getElementById("btnAdd").classList.remove("disabled");
}
