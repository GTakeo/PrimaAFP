--create tablesapce

CREATE TABLESPACE ts_primaAFPdev
DATAFILE 'C:\oraclexe\app\oracle\oradata\XE\primaAFPtablespace.dbf'
SIZE 100M AUTOEXTEND ON NEXT 5M MAXSIZE 400M
EXTENT MANAGEMENT LOCAL
SEGMENT SPACE MANAGEMENT AUTO
LOGGING;

CREATE TEMPORARY TABLESPACE temp_primaAFPdev
TEMPFILE 'C:\oraclexe\app\oracle\oradata\XE\primaAFPtemptablespace.dbf'
SIZE 50m
EXTENT MANAGEMENT LOCAL;

/*--SEGURIDAD*/
-- creamos y asignamos el perfil para el usuario creado
CREATE PROFILE dba_primaAFPdev LIMIT 
FAILED_LOGIN_ATTEMPTS UNLIMITED /*-numero de intentos de inicio de sesion fallidos antes del bloqueo-*/
PASSWORD_LIFE_TIME UNLIMITED /*-tiempo de vida de la contrase�a en dias, despues de los cuales la contrase�a expira-*/
PASSWORD_REUSE_TIME UNLIMITED /*-numero de dias antes de que una contrase�a se pueda reutilizar-*/
PASSWORD_REUSE_MAX UNLIMITED /*-numero maximo de veces que una contrase�a se pueda reutilizar-*/
PASSWORD_LOCK_TIME UNLIMITED /*-numero de dias que la cuenta esta bloqueada despues del numero de intentos fallidos-*/
PASSWORD_GRACE_TIME UNLIMITED;/*-periodo de gracia en dias para cambiar la contrase�a despues del primer inicio de sesion satisfactorio luego que la contrase�a expiro-*/

/*--USUARIO ADMINISTRADOR*/
-- Creamos un usuario y lo asociamos al esquema
CREATE USER bd_primaAFPdev
IDENTIFIED BY "123456"
DEFAULT TABLESPACE ts_primaAFPdev
TEMPORARY TABLESPACE temp_primaAFPdev
PROFILE dba_primaAFPdev
QUOTA 10M ON ts_primaAFPdev;

/* --ROL PRINCIPALES PARA EL ADMINISTRADOR()*/
GRANT RESOURCE TO bd_primaAFPdev;
GRANT CONNECT TO bd_primaAFPdev;
/*------*/