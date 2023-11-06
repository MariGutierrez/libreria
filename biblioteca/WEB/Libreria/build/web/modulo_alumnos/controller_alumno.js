let usuarioss = [];
let indexAlumnoSeleccionado;

export function inicializar() {
    refrescarTabla();
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

export function delet() {
    let datos = null;
    let params = null;

    let alumno = new Object();
    alumno.persona = new Object();
    alumno.usuario = new Object();

    alumno.id_alumno = parseInt(document.getElementById("id_alumno").value);

    alumno.persona.nombre = document.getElementById("nombre").value;
    alumno.persona.primer_apellido = document.getElementById("primer_apellido").value;
    alumno.persona.segundo_apellido = document.getElementById("segundo_apellido").value;
    alumno.persona.email = document.getElementById("email").value;
    alumno.persona.telefono = document.getElementById("telefono").value;
    alumno.persona.edad = parseInt(document.getElementById("edad").value);
    alumno.persona.fecha_nacimiento = document.getElementById("fecha_nacimiento").value;
    alumno.usuario.nombre_usuario = document.getElementById("nombre_usuario").value;
    alumno.usuario.contrasenia = document.getElementById("contrasenia").value;
    alumno.usuario.rol = document.getElementById("rola").value;
    alumno.matricula = document.getElementById("matricula").value;

    datos = {
        datosAlumno: JSON.stringify(alumno)
    };

    params = new URLSearchParams(datos);

    fetch("api/alumno/delete", {
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

                Swal.fire('', "Datos del alumno eliminados correctamente", 'success');
                refrescarTabla();
                clean();
            });
}

export function selectAlumno(id_alumno) {
    const alumnoSeleccionado = usuarioss.find(alumno => alumno.id_alumno === id_alumno);

    if (alumnoSeleccionado) {
        document.getElementById("id_alumno").value = alumnoSeleccionado.id_alumno;
        document.getElementById("id_persona").value = alumnoSeleccionado.persona.id_persona;
        document.getElementById("id_usuario").value = alumnoSeleccionado.usuario.id_usuario;
        document.getElementById("nombre").value = alumnoSeleccionado.persona.nombre;
        document.getElementById("primer_apellido").value = alumnoSeleccionado.persona.primer_apellido;
        document.getElementById("segundo_apellido").value = alumnoSeleccionado.persona.segundo_apellido;
        document.getElementById("email").value = alumnoSeleccionado.persona.email;
        document.getElementById("telefono").value = alumnoSeleccionado.persona.telefono;
        document.getElementById("edad").value = alumnoSeleccionado.persona.edad;
        document.getElementById("fecha_nacimiento").value = alumnoSeleccionado.persona.fecha_nacimiento;
        document.getElementById("nombre_usuario").value = alumnoSeleccionado.usuario.nombre_usuario;
        document.getElementById("contrasenia").value = alumnoSeleccionado.usuario.contrasenia;
        document.getElementById("rola").value = alumnoSeleccionado.usuario.rol;
        document.getElementById("matricula").value = alumnoSeleccionado.matricula;
    }
}

export function clean() {
    document.getElementById("id_alumno").value = "";
    document.getElementById("id_persona").value = "";
    document.getElementById("id_usuario").value = "";
    document.getElementById("nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("email").value = "";
    document.getElementById("telefono").value = "";
    document.getElementById("edad").value = "";
    document.getElementById("fecha_nacimiento").value = "";
    document.getElementById("nombre_usuario").value = "";
    document.getElementById("contrasenia").value = "";
    document.getElementById("rola").value = "";
    document.getElementById("matricula").value = "";
}
