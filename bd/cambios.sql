  ALTER TABLE tb_colaborador ADD COLUMN observacion character varying(200);
  ALTER TABLE tb_padrino ADD COLUMN observacion character varying(200);


  ALTER TABLE tb_trabajador  ADD COLUMN estatus_trabajador numeric(1,0);
  COMMENT ON COLUMN tb_trabajador.estatus_trabajador IS 'ENUM:Activo:Inactivo';

  ALTER TABLE tb_colaborador ADD COLUMN estatus_colaborador numeric(1,0);
  COMMENT ON COLUMN tb_colaborador.estatus_colaborador IS 'ENUM:Activo:Inactivo';


  ALTER TABLE tb_voluntario ADD COLUMN  fk_motivo numeric(6,0);
  ALTER TABLE tb_padrino ADD COLUMN  fk_motivo numeric(6,0);
  ALTER TABLE tb_colaborador ADD COLUMN  fk_motivo numeric(6,0);
  ALTER TABLE tb_trabajador ADD COLUMN  fk_motivo numeric(6,0);


  ALTER TABLE tb_trabajador
    ADD CONSTRAINT re_tb_trabajador3 FOREIGN KEY (fk_motivo)
        REFERENCES tb_motivo (id_motivo) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT;

  ALTER TABLE tb_colaborador
    ADD CONSTRAINT re_tb_colaborador3 FOREIGN KEY (fk_motivo)
        REFERENCES tb_motivo (id_motivo) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT;

  ALTER TABLE tb_voluntario
    ADD CONSTRAINT re_tb_voluntario3 FOREIGN KEY (fk_motivo)
        REFERENCES tb_motivo (id_motivo) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT;

  ALTER TABLE tb_padrino
    ADD CONSTRAINT re_tb_padrino FOREIGN KEY (fk_motivo)
        REFERENCES tb_motivo (id_motivo) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT; 

  ALTER TABLE tb_participacion ADD COLUMN formulario boolean;
  ALTER TABLE tb_participacion ADD COLUMN tipo_formulario numeric(1,0);
  COMMENT ON COLUMN tb_participacion.tipo_formulario IS 'ENUM:Padrino:Voluntario';


 ALTER TABLE tb_contacto_portal ADD COLUMN respuesta character varying(400);