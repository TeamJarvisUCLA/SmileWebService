
/*==============================================================*/
/* Table: tb_sesion                                             */
/*==============================================================*/
CREATE SEQUENCE tb_sesion_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

create table tb_sesion 
(
   id_sesion            numeric(9)             not null DEFAULT nextval('tb_sesion_id_seq'::regclass),
   fk_usuario           numeric(9)             not null,
   estado               character varying(1)   not null,
   direccion_ip         character varying(100) not null,
   access_token         character varying(250) not null,
   fecha_inicio         numeric(16)            not null,
   fecha_fin            numeric(16)            not null,
   constraint pk_tb_sesion primary key (id_sesion)
);


/*==============================================================*/
/* Table: tb_dispositivo_android                                */
/*==============================================================*/
CREATE SEQUENCE tb_dispositivo_android_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

create table tb_dispositivo_android 
(
   id_dispositivo_android numeric(9)             not null DEFAULT nextval('tb_dispositivo_android_id_seq'::regclass),
   fk_usuario             numeric(9)             not null,
   registration_id        character varying(1)   not null,
   constraint pk_tb_dispositivo_android primary key (id_dispositivo_android)
);

/*==============================================================*/
/* Table: tb_mensaje_sistema                                    */
/*==============================================================*/
CREATE SEQUENCE tb_mensaje_sistema_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

create table tb_mensaje_sistema 
(
   id_mensaje_sistema   numeric(9)             not null DEFAULT nextval('tb_mensaje_sistema_id_seq'::regclass),
   fk_usuario           numeric(9)             not null,
   contenido            character varying(60) not null,
   fecha                numeric(16)            not null,
   constraint pk_tb_mensaje_sistema primary key (id_mensaje_sistema)
);

/*==============================================================*/
/* Table: tb_usuario                                            */
/*==============================================================*/
CREATE SEQUENCE tb_usuario_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_usuario 
(
   id_usuario           numeric(9)             not null DEFAULT nextval('tb_usuario_id_seq'::regclass),
   clave                character varying(300) not null,
   correo               character varying(60)  not null,
   estatus              character varying(1)   not null,
   fk_perfil            numeric(9),
   constraint pk_tb_usuario primary key (id_usuario)
);

/*==============================================================*/
/* Table: tb_rol                                                */
/*==============================================================*/
CREATE SEQUENCE tb_rol_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_rol 
(
   id_rol               numeric(9)                   not null DEFAULT nextval('tb_rol_id_seq'::regclass),
   nombre               character varying(100) not null,
   constraint pk_tb_rol primary key (id_rol)
);

/*==============================================================*/
/* Table: tb_usuario_rol                                        */
/*==============================================================*/
CREATE SEQUENCE tb_usuario_rol_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_usuario_rol 
(
   id_usuario_rol       numeric(9)              not null DEFAULT nextval('tb_usuario_rol_id_seq'::regclass),
   fk_usuario           numeric(9)              not null,
   fk_rol               numeric(9)              not null,
   constraint pk_tb_usuario_rol primary key (id_usuario_rol)
);

/*==============================================================*/
/* Table: tb_icon_sclass                                        */
/*==============================================================*/
CREATE SEQUENCE tb_icon_sclass_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_icon_sclass 
(
   id_icon_sclass       numeric(9)               not null DEFAULT nextval('tb_icon_sclass_id_seq'::regclass),
   nombre            character varying(70) not null,
   constraint pk_tb_icon_sclass primary key (id_icon_sclass)
);

/*==============================================================*/
/* Table: tb_sclass                                        */
/*==============================================================*/
CREATE SEQUENCE tb_sclass_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_sclass 
(
   id_sclass       numeric(9)               not null DEFAULT nextval('tb_sclass_id_seq'::regclass),
   nombre            character varying(70) not null,
   constraint pk_tb_sclass primary key (id_sclass)
);


/*==============================================================*/
/* Table: tb_vista                                              */
/*==============================================================*/
CREATE SEQUENCE tb_vista_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_vista 
(
   id_vista          numeric(9)                   not null DEFAULT nextval('tb_vista_id_seq'::regclass),
   nombre            character varying(100) not null,
   descripcion       character varying(255) not null,
   archivo_zul       character varying(200) not null,
   constraint pk_tb_vista primary key (id_vista)
);

/*==============================================================*/
/* Table: tb_nodo_menu                                          */
/*==============================================================*/
CREATE SEQUENCE tb_nodo_menu_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_nodo_menu
(
   id_nodo_menu         numeric(9)                   not null DEFAULT nextval('tb_nodo_menu_id_seq'::regclass),
   fk_nodo_menu         numeric(9),
   nombre               character varying(100) not null,
   tipo_nodo_menu       numeric(1)                not null,
   fk_icon_sclass       numeric(9)                   not null,
   fk_vista             numeric(9),
   constraint pk_tb_nodo_menu primary key (id_nodo_menu)
);

comment on column tb_nodo_menu.tipo_nodo_menu is
'ENUM:Raiz:Carpeta:Transaccion';

/*==============================================================*/
/* Table: tb_vista_operacion_basico                             */
/*==============================================================*/
CREATE SEQUENCE tb_vista_operacion_basico_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_vista_operacion_basico
(
   id_vista_operacion_basico  numeric(9)        not null DEFAULT nextval('tb_vista_operacion_basico_id_seq'::regclass),
   operacion                  numeric(1)     not null,
   fk_vista                   numeric(9)        not null,
   fk_icon_sclass             numeric(9)                   not null,
   fk_sclass	              numeric(9)             not null,
   tooltiptext                character varying(75)  not null,
   nombre                     character varying(55)  not null,
   constraint pk_tb_vista_operacion_basico primary key (id_vista_operacion_basico)
);

comment on column tb_vista_operacion_basico.operacion is
'ENUM:Custom5:Incluir:Modificar:Eliminar:Consultar:Custom1:Custom2:Custom3:Custom4';

/*==============================================================*/
/* Table: tb_vista_operacion_custom                             */
/*==============================================================*/
CREATE SEQUENCE tb_vista_operacion_custom_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_vista_operacion_custom
(
   id_vista_operacion_custom  numeric(9)                   not null DEFAULT nextval('tb_vista_operacion_custom_id_seq'::regclass),
   operacion                  numeric(1)                not null,
   fk_vista                   numeric(9)                   not null,
   fk_icon_sclass             numeric(9)                   not null,
   fk_sclass	              numeric(9)             not null,
   tooltiptext                character varying(75)  not null,
   nombre                     character varying(55)  not null,
   constraint pk_tb_vista_operacion_custom primary key (id_vista_operacion_custom)
);

comment on column tb_vista_operacion_custom.operacion is
'ENUM:Custom5:Incluir:Modificar:Eliminar:Consultar:Custom1:Custom2:Custom3:Custom4';

/*==============================================================*/
/* Table: tb_permiso_seguridad                                  */
/*==============================================================*/
CREATE SEQUENCE tb_permiso_seguridad_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
create table tb_permiso_seguridad 
(
   id_permiso_seguridad numeric(9)              not null DEFAULT nextval('tb_permiso_seguridad_id_seq'::regclass),
   fk_nodo_menu         numeric(9)              not null,
   fk_rol               numeric(9)              not null,
   operacion            numeric(1)           not null,
   constraint pk_tb_permiso_seguridad primary key (id_permiso_seguridad)
);

comment on column tb_permiso_seguridad.operacion is
'ENUM:Custom5:Incluir:Modificar:Eliminar:Consultar:Custom1:Custom2:Custom3:Custom4';

/*==============================================================*/
/* Table: tb_tabla                                              */
/*==============================================================*/
CREATE SEQUENCE tb_tabla_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE tb_tabla
(
  id_tabla  numeric(9)  	   not null DEFAULT nextval('tb_tabla_id_seq'::regclass), 
  nombre    character varying(100) not null,
  constraint pk_tb_tabla PRIMARY KEY(id_tabla)
);

/*==============================================================*/
/* Table: tb_metodo_dao                                         */
/*==============================================================*/
CREATE SEQUENCE tb_metodo_dao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE tb_metodo_dao
(
  id_metodo_dao     numeric(9)  	   not null DEFAULT nextval('tb_metodo_dao_id_seq'::regclass),  
  nombre            character varying(100) not null,
  constraint pk_tb_metodo_dao PRIMARY KEY(id_metodo_dao)
);

/*==============================================================*/
/* Table: tb_auditoria                                          */
/*==============================================================*/
CREATE SEQUENCE tb_auditoria_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE tb_auditoria
(
  id_auditoria        numeric(9)  	            not null DEFAULT nextval('tb_auditoria_id_seq'::regclass),
  fk_sesion           numeric(9)  	            not null,
  fk_tabla            numeric(9)  	            not null,
  accion              numeric(9)	            not null,
  fk_metodo_dao       numeric(9)  	            not null,
  fk_metodo_dao_raiz  numeric(9)  	            not null,
  registro_id         numeric(9)  	            not null,
  datos               text                          not null,
  fecha               numeric(16)                    not null,
  constraint pk_tb_auditoria PRIMARY KEY(id_auditoria)
);

/*==============================================================*/
/* Table: tb_perfil                                             */
/*==============================================================*/
CREATE SEQUENCE tb_perfil_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

create table tb_perfil 
(
   id_perfil            numeric(9)             not null DEFAULT nextval('tb_perfil_id_seq'::regclass),
   identificacion       character varying(35)  not null,
   nombre               character varying(150) not null,
   edad                 numeric(2)             not null,
   sexo                 numeric(1)             not null,
   fecha_nacimiento     numeric(16)            not null,
   telefono_1           character varying(25)  not null,
   telefono_2           character varying(25)  not null,
   telefono_3           character varying(25)  not null,
   direccion            character varying(250) not null,
   pais                 character varying(45)  not null,
   twitter              character varying(100) not null,
   instagram            character varying(100) not null,
   linkedin             character varying(100) not null,
   constraint pk_tb_perfil primary key (id_perfil)
);

comment on column tb_perfil.sexo is
'ENUM:Masculino:Femenino';

alter table tb_sesion
   add constraint fk_tb_sesion_r_tb_usuario foreign key (fk_usuario)
      references tb_usuario (id_usuario);
      
alter table tb_dispositivo_android
   add constraint fk_tb_dispositivo_android_r_tb_usuario foreign key (fk_usuario)
      references tb_usuario (id_usuario);
      
alter table tb_mensaje_sistema
   add constraint fk_tb_mensaje_sistema_r_tb_usuario foreign key (fk_usuario)
      references tb_usuario (id_usuario);

alter table tb_usuario
   add constraint fk_tb_usuario_r_tb_perfil foreign key (fk_perfil)
      references tb_perfil (id_perfil);
      
alter table tb_usuario_rol
   add constraint fk_tb_usuario_rol_r_tb_usuario foreign key (fk_usuario)
      references tb_usuario (id_usuario);

alter table tb_usuario_rol
   add constraint fk_tb_usuario_rol_r_tb_rol foreign key (fk_rol)
      references tb_rol (id_rol);
      
alter table tb_permiso_seguridad
   add constraint fk_tb_permiso_seguridad_r_tb_rol foreign key (fk_rol)
      references tb_rol (id_rol);

alter table tb_permiso_seguridad
   add constraint fk_tb_permiso_seguridad_r_tb_nodo_menu foreign key (fk_nodo_menu)
      references tb_nodo_menu (id_nodo_menu);
      
alter table tb_nodo_menu
   add constraint fk_tb_nodo_menu_r_tb_nodo_menu foreign key (fk_nodo_menu)
      references tb_nodo_menu (id_nodo_menu);
      
alter table tb_nodo_menu
   add constraint fk_tb_nodo_menu_r_tb_icon_sclass foreign key (fk_icon_sclass)
      references tb_icon_sclass (id_icon_sclass);

alter table tb_nodo_menu
   add constraint fk_tb_nodo_menu_r_tb_vista foreign key (fk_vista)
      references tb_vista (id_vista);
      
 alter table tb_vista_operacion_basico
   add constraint fk_tb_vista_operacion_basico_r_tb_vista foreign key (fk_vista)
      references tb_vista (id_vista);
      
alter table tb_vista_operacion_basico
   add constraint fk_tb_vista_operacion_basico_r_tb_icon_sclass foreign key (fk_icon_sclass)
      references tb_icon_sclass (id_icon_sclass);
      
alter table tb_vista_operacion_basico
   add constraint fk_tb_vista_operacion_basico_r_tb_sclass foreign key (fk_sclass)
      references tb_sclass (id_sclass);

alter table tb_vista_operacion_custom
   add constraint fk_tb_vista_operacion_custom_r_tb_vista foreign key (fk_vista)
      references tb_vista (id_vista);
      
alter table tb_vista_operacion_custom
   add constraint fk_tb_vista_operacion_custom_r_tb_icon_sclass foreign key (fk_icon_sclass)
      references tb_icon_sclass (id_icon_sclass);
      
alter table tb_vista_operacion_custom
   add constraint fk_tb_vista_operacion_custom_r_tb_sclass foreign key (fk_sclass)
      references tb_sclass (id_sclass);
      
alter table tb_auditoria
   add constraint fk_tb_auditoria_r_tb_tabla foreign key (fk_tabla)
      references tb_tabla (id_tabla);

alter table tb_auditoria
   add constraint fk_tb_auditoria_r_tb_metodo_dao foreign key (fk_metodo_dao)
      references tb_metodo_dao (id_metodo_dao);
      
      alter table tb_auditoria
   add constraint fk_tb_auditoria_r_tb_metodo_dao_2 foreign key (fk_metodo_dao_raiz)
      references tb_metodo_dao (id_metodo_dao);

alter table tb_auditoria
   add constraint fk_tb_auditoria_r_tb_sesion foreign key (fk_sesion)
      references tb_sesion (id_sesion);
      
      
      
INSERT INTO TB_TABLA (nombre) VALUES (1, 'tb_vista_operacion_basico');
INSERT INTO TB_TABLA (nombre) VALUES (2, 'tb_usuario');
INSERT INTO TB_TABLA (nombre) VALUES (3, 'tb_permiso_seguridad');
INSERT INTO TB_TABLA (nombre) VALUES (4, 'tb_icon_sclass');
INSERT INTO TB_TABLA (nombre) VALUES (5, 'tb_vista_operacion_custom');
INSERT INTO TB_TABLA (nombre) VALUES (6, 'tb_tabla');
INSERT INTO TB_TABLA (nombre) VALUES (7, 'tb_usuario_rol');
INSERT INTO TB_TABLA (nombre) VALUES (8, 'tb_vista');
INSERT INTO TB_TABLA (nombre) VALUES (9, 'tb_auditoria');
INSERT INTO TB_TABLA (nombre) VALUES (10, 'tb_sesion');
INSERT INTO TB_TABLA (nombre) VALUES (11, 'tb_nodo_menu');
INSERT INTO TB_TABLA (nombre) VALUES (12, 'tb_rol');
INSERT INTO TB_TABLA (nombre) VALUES (13, 'tb_metodo_dao');
INSERT INTO TB_TABLA (nombre) VALUES (14, 'tb_mensaje_sistema');
INSERT INTO TB_TABLA (nombre) VALUES (15, 'tb_perfil');
INSERT INTO TB_TABLA (nombre) VALUES (16, 'tb_dispositivo_android');

INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionBasicoService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PermisoSeguridadService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('IconSclassService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaOperacionCustomService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('TablaService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('UsuarioRolService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('VistaService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('AuditoriaService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('SesionService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('NodoMenuService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('RolService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MetodoDaoService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('MensajeSistemaService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('PerfilService.MODIFICAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.CONSULTAR_UNO');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.CONSULTAR_TODOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.CONSULTAR_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.CONSULTAR_PAGINACION_CRITERIOS');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.ELIMINAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.CONTAR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.INCLUIR');
INSERT INTO TB_METODO_DAO (nombre) VALUES ('DispositivoAndroidService.MODIFICAR');

INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stethoscope');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-user-md');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-wheelchair');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bluetooth');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bluetooth-b');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-codiepie');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-credit-card-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-edge');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fort-awesome');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hashtag');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mixcloud');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-modx');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pause-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pause-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-percent');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-product-hunt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reddit-alien');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-scribd');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shopping-bag');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shopping-basket');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stop-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stop-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-usb');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-adjust');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-anchor');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-archive');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-area-chart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrows');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrows-h');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrows-v');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-asterisk');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-at');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-automobile');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-balance-scale');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ban');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bank');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bar-chart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bar-chart-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-barcode');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bars');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-0');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-1');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-2');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-3');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-4');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-empty');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-full');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-half');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-quarter');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-battery-three-quarters');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bed');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-beer');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bell');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bell-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bell-slash');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bell-slash-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bicycle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-binoculars');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-birthday-cake');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bolt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bomb');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-book');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bookmark');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bookmark-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-briefcase');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bug');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-building');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-building-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bullhorn');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bullseye');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cab');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calculator');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar-check-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar-minus-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar-plus-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-calendar-times-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-camera');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-camera-retro');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-car');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-square-o-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-square-o-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-square-o-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-square-o-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cart-arrow-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cart-plus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-certificate');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-check');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-check-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-check-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-check-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-check-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-child');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-circle-o-notch');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-circle-thin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-clock-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-clone');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-close');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cloud');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cloud-download');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cloud-upload');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-code');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-code-fork');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-coffee');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cog');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cogs');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-comment');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-comment-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-commenting');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-commenting-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-comments');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-comments-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-compass');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-copyright');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-creative-commons');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-credit-card');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-crop');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-crosshairs');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cube');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cubes');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cutlery');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dashboard');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-database');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-desktop');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-diamond');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dot-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-download');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-edit');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ellipsis-h');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ellipsis-v');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-envelope');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-envelope-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-envelope-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eraser');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-exchange');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-exclamation');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-exclamation-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-exclamation-triangle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-external-link');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-external-link-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eye');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eye-slash');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eyedropper');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fax');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-feed');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-female');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fighter-jet');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-archive-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-audio-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-code-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-excel-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-image-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-movie-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-pdf-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-photo-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-picture-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-powerpoint-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-sound-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-video-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-word-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-zip-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-film');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-filter');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fire');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fire-extinguisher');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flag');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flag-checkered');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flag-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flash');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flask');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-folder');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-folder-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-folder-open');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-folder-open-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-frown-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-futbol-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gamepad');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gavel');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gear');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gears');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gift');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-glass');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-globe');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-graduation-cap');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-group');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-grab-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-lizard-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-paper-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-peace-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-pointer-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-rock-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-scissors-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-spock-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-stop-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hdd-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-headphones');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-heart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-heart-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-heartbeat');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-history');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-home');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hotel');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-1');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-2');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-3');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-end');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-half');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hourglass-start');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-i-cursor');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-image');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-inbox');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-industry');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-info');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-info-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-institution');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-key');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-keyboard-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-language');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-laptop');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-leaf');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-legal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-lemon-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-level-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-level-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-life-bouy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-life-buoy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-life-ring');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-life-saver');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-lightbulb-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-line-chart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-location-arrow');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-lock');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-magic');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-magnet');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mail-forward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mail-reply');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mail-reply-all');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-male');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-map');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-map-marker');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-map-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-map-pin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-map-signs');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-meh-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-microphone');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-microphone-slash');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-minus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-minus-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-minus-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-minus-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mobile');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mobile-phone');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-money');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-moon-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mortar-board');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-motorcycle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mouse-pointer');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-music');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-navicon');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-newspaper-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-object-group');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-object-ungroup');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paint-brush');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paper-plane');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paper-plane-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paw');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pencil');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pencil-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pencil-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-phone');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-phone-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-photo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-picture-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pie-chart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plane');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plug');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plus-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plus-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-plus-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-power-off');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-print');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-puzzle-piece');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-qrcode');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-question');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-question-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-quote-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-quote-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-random');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-recycle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-refresh');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-registered');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-remove');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reorder');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reply');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reply-all');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-retweet');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-road');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rocket');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rss');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rss-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-search');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-search-minus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-search-plus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-send');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-send-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-server');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-share');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-share-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-share-alt-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-share-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-share-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shield');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ship');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shopping-cart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sign-in');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sign-out');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-signal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sitemap');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sliders');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-smile-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-soccer-ball-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-alpha-asc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-alpha-desc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-amount-asc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-amount-desc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-asc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-desc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-numeric-asc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-numeric-desc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sort-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-space-shuttle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-spinner');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-spoon');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-square-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star-half');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star-half-empty');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star-half-full');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star-half-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-star-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sticky-note');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sticky-note-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-street-view');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-suitcase');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sun-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-support');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tablet');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tachometer');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tag');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tags');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tasks');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-taxi');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-television');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-terminal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-thumb-tack');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-thumbs-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-thumbs-o-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-thumbs-o-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-thumbs-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ticket');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-times');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-times-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-times-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tint');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-off');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-on');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-toggle-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-trademark');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-trash');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-trash-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tree');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-trophy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-truck');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tty');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tv');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-umbrella');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-university');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-unlock');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-unlock-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-unsorted');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-upload');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-user');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-user-plus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-user-secret');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-user-times');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-users');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-video-camera');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-volume-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-volume-off');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-volume-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-warning');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-wrench');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-o-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-o-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-o-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hand-o-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ambulance');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-subway');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-train');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-genderless');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-intersex');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mars');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mars-double');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mars-stroke');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mars-stroke-h');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mars-stroke-v');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-mercury');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-neuter');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-transgender');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-transgender-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-venus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-venus-double');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-venus-mars');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-text');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-file-text-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-info-circle fa-lg fa-li');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-amex');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-diners-club');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-discover');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-jcb');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-mastercard');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-paypal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-stripe');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cc-visa');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-google-wallet');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paypal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bitcoin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-btc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cny');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dollar');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eur');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-euro');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gbp');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gg');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gg-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ils');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-inr');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-jpy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-krw');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rmb');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rouble');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rub');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ruble');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rupee');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shekel');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sheqel');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-try');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-turkish-lira');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-usd');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-won');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-yen');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-align-center');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-align-justify');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-align-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-align-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bold');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chain');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chain-broken');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-clipboard');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-columns');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-copy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-cut');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dedent');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-files-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-floppy-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-font');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-header');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-indent');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-italic');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-link');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-list');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-list-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-list-ol');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-list-ul');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-outdent');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paperclip');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paragraph');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-paste');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-repeat');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rotate-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rotate-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-save');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-scissors');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-strikethrough');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-subscript');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-superscript');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-table');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-text-height');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-text-width');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-th');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-th-large');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-th-list');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-underline');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-undo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-unlink');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-double-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-double-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-double-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-double-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angle-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-o-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-o-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-o-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-o-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-circle-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrow-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-arrows-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-caret-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-circle-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-circle-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-circle-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-circle-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chevron-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-long-arrow-down');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-long-arrow-left');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-long-arrow-right');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-long-arrow-up');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-backward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-compress');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-eject');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-expand');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fast-backward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fast-forward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-forward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pause');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-play');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-play-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-play-circle-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-step-backward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-step-forward');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stop');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-youtube-play');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-500px');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-adn');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-amazon');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-android');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-angellist');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-apple');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-behance');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-behance-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bitbucket');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-bitbucket-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-black-tie');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-buysellads');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-chrome');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-codepen');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-connectdevelop');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-contao');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-css3');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dashcube');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-delicious');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-deviantart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-digg');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dribbble');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-dropbox');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-drupal');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-empire');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-expeditedssl');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-facebook');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-facebook-f');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-facebook-official');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-facebook-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-firefox');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-flickr');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-fonticons');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-forumbee');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-foursquare');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ge');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-get-pocket');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-git');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-git-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-github');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-github-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-github-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gittip');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-google');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-google-plus');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-google-plus-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-gratipay');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hacker-news');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-houzz');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-html5');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-instagram');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-internet-explorer');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ioxhost');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-joomla');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-jsfiddle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-lastfm');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-lastfm-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-leanpub');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-linkedin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-linkedin-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-linux');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-maxcdn');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-meanpath');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-medium');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-odnoklassniki');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-odnoklassniki-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-opencart');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-openid');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-opera');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-optin-monster');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pagelines');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pied-piper');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pied-piper-alt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pinterest');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pinterest-p');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-pinterest-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-qq');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-ra');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-rebel');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reddit');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-reddit-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-renren');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-safari');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-sellsy');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-shirtsinbulk');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-simplybuilt');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-skyatlas');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-skype');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-slack');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-slideshare');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-soundcloud');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-spotify');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stack-exchange');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stack-overflow');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-steam');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-steam-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stumbleupon');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-stumbleupon-circle');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tencent-weibo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-trello');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tripadvisor');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tumblr');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-tumblr-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-twitch');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-twitter');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-twitter-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-viacoin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-vimeo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-vimeo-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-vine');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-vk');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-wechat');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-weibo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-weixin');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-whatsapp');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-wikipedia-w');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-windows');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-wordpress');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-xing');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-xing-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-y-combinator');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-y-combinator-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-yahoo');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-yc');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-yc-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-yelp');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-youtube');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-youtube-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-h-square');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-hospital-o');
INSERT INTO tb_icon_sclass (nombre) VALUES ('fa fa-medkit');

INSERT INTO tb_sclass (nombre) VALUES ('red lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('red lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('red lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('red lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('red lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('red');
INSERT INTO tb_sclass (nombre) VALUES ('red darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('red darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('red darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('red darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('red accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('red accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('red accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('red accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('pink lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('pink lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('pink lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('pink lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('pink lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('pink');
INSERT INTO tb_sclass (nombre) VALUES ('pink darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('pink darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('pink darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('pink darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('pink accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('pink accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('pink accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('pink accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('purple lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('purple lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('purple lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('purple lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('purple lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('purple');
INSERT INTO tb_sclass (nombre) VALUES ('purple darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('purple darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('purple darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('purple darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('purple accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('purple accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('purple accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('purple accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-purple accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('indigo lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('indigo lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('indigo lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('indigo lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('indigo lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('indigo');
INSERT INTO tb_sclass (nombre) VALUES ('indigo darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('indigo darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('indigo darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('indigo darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('indigo accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('indigo accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('indigo accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('indigo accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('blue lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('blue lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('blue lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('blue lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('blue lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('blue');
INSERT INTO tb_sclass (nombre) VALUES ('blue darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('blue darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('blue darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('blue darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('blue accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('blue accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('blue accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('blue accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-blue accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('cyan lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('cyan lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('cyan lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('cyan lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('cyan lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('cyan');
INSERT INTO tb_sclass (nombre) VALUES ('cyan darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('cyan darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('cyan darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('cyan darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('cyan accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('cyan accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('cyan accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('cyan accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('teal lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('teal lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('teal lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('teal lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('teal lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('teal');
INSERT INTO tb_sclass (nombre) VALUES ('teal darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('teal darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('teal darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('teal darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('teal accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('teal accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('teal accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('teal accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('green lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('green lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('green lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('green lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('green lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('green');
INSERT INTO tb_sclass (nombre) VALUES ('green darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('green darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('green darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('green darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('green accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('green accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('green accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('green accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-green lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('light-green lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-green lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-green lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-green lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-green');
INSERT INTO tb_sclass (nombre) VALUES ('light-green darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-green darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-green darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-green darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('light-green accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('light-green accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('light-green accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('light-green accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('lime lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('lime lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('lime lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('lime lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('lime lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('lime');
INSERT INTO tb_sclass (nombre) VALUES ('lime darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('lime darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('lime darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('lime darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('lime accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('lime accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('lime accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('lime accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('yellow lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('yellow lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('yellow lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('yellow lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('yellow lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('yellow');
INSERT INTO tb_sclass (nombre) VALUES ('yellow darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('yellow darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('yellow darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('yellow darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('yellow accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('yellow accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('yellow accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('yellow accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('amber lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('amber lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('amber lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('amber lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('amber lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('amber');
INSERT INTO tb_sclass (nombre) VALUES ('amber darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('amber darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('amber darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('amber darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('amber accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('amber accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('amber accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('amber accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('orange lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('orange lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('orange lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('orange lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('orange lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('orange');
INSERT INTO tb_sclass (nombre) VALUES ('orange darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('orange darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('orange darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('orange darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('orange accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('orange accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('orange accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('orange accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange accent-1');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange accent-2');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange accent-3');
INSERT INTO tb_sclass (nombre) VALUES ('deep-orange accent-4');
INSERT INTO tb_sclass (nombre) VALUES ('brown lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('brown lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('brown lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('brown lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('brown lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('brown');
INSERT INTO tb_sclass (nombre) VALUES ('brown darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('brown darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('brown darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('brown darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('grey lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('grey lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('grey lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('grey lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('grey lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('grey');
INSERT INTO tb_sclass (nombre) VALUES ('grey darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('grey darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('grey darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('grey darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey lighten-5');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey lighten-4');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey lighten-3');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey lighten-2');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey lighten-1');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey darken-1');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey darken-2');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey darken-3');
INSERT INTO tb_sclass (nombre) VALUES ('blue-grey darken-4');
INSERT INTO tb_sclass (nombre) VALUES ('black');
INSERT INTO tb_sclass (nombre) VALUES ('white');
INSERT INTO tb_sclass (nombre) VALUES ('transparent');

INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Roles', 'Permite la gestión de los roles del Sistema.', 'vista/principalRol.zul');
INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Usuarios', 'Permite la gestión de los usuarios del Sistema.', 'vista/principalUsuario.zul');
INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Menú', 'Permite la gestión del menú que podrá ser visualizado en el sistema.', 'vista/seguridad/menu/index.zul');
INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Mensajes de Sistema', 'Permite enviar mensajes de sistema a los usuarios conectados.', 'vista/principalMensajeSistema.zul');
INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Usuarios Activos', 'Permite visualizar a los usuarios activos.', 'vista/principalUsuariosActivos.zul');
INSERT INTO tb_vista (nombre, descripcion, archivo_zul) VALUES ('Permisos', 'Permite modificar los permisos del menú, para cada rol.', 'vista/seguridad/permisos/index.zul');

INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (1, 1, 284, 'Incluir', 'Incluir', 132);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (2, 1, 133, 'Modificar', 'Modificar', 188);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (3, 1, 383, 'Eliminar', 'Eliminar', 6);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (4, 1, 146, 'Consultar', 'Consultar', 76);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (1, 2, 284, 'Incluir', 'Incluir', 132);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (2, 2, 133, 'Modificar', 'Modificar', 188);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (4, 2, 146, 'Consultar', 'Consultar', 76);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (1, 3, 284, 'Incluir', 'Incluir', 132);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (2, 3, 133, 'Modificar', 'Modificar', 188);
INSERT INTO tb_vista_operacion_basico (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (3, 3, 383, 'Eliminar', 'Eliminar', 6);

INSERT INTO tb_vista_operacion_custom (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (5, 2, 298, 'Reestablecer Contraseña', 'Reestablecer Contraseña', 122);
INSERT INTO tb_vista_operacion_custom (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (0, 4, 69, 'Publicar Mensade de Sistema', 'Publicar Mensade de Sistema', 94);
INSERT INTO tb_vista_operacion_custom (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (6, 5, 488, 'Ver Sesiones del Usuario', 'Ver Sesiones del Usuario', 164);
INSERT INTO tb_vista_operacion_custom (operacion, fk_vista, fk_icon_sclass, tooltiptext, nombre, fk_sclass) VALUES (5, 6, 223, 'Modifcar Permisos', 'Modifcar Permisos', 94);



