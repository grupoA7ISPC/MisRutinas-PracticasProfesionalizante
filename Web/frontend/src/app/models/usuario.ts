export class Usuario {
  id: number;
  id_rol: number;
  nombre: string;
  apellido: string;
  email: string;
  fec_nac: string;
  dni: number;
  tel: string;

  constructor(data: any) {
    this.id = data.id;
    this.id_rol = data.id_rol;
    this.nombre = data.nombre;
    this.apellido = data.apellido;
    this.email = data.email;
    this.fec_nac = data.fec_nac;
    this.dni = data.dni;
    this.tel = data.tel;
  }
}

export class UsuarioDTO {
  nombre: string = "";
  apellido: string = "";
  email: string = "";
  password: string = "";
  tel: string = "";
  dni: string = "";
  fec_nac: string = "";
}

export class UsuarioLoginDTO{
  email: string | null | undefined = "";
  password: string | null | undefined = "";
}
