insert into perfis(id, nome)
values (1, 'USER');

insert into usuario(id, nome, senha, sobrenome, telefone, is_confirmed)
values (1, cleber, 123456, Junioe, 12345678901, true);

insert into motorista(id, nome, email, telefone, veiculo)
values (1, Carlos, carlos@email.com, 12345678902, 1);

insert into veiculo(id, veiculo_ano, veiculo_placa, veiculo_tipo)
values (1, 02-02-2020, Esportivo);

insert into usuario_perfis(usuario_id, perfis_id)
values (1, 1);