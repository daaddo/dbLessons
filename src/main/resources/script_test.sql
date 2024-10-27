# questo script verrà eseguito solo dopo script_base in ambiente di testing
use spring_db_setup;

delete from user where username = 'John';