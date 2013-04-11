delimiter //
CREATE PROCEDURE verifyUser(IN user VARCHAR(15), IN pwd VARCHAR(15))
BEGIN
IF EXISTS (SELECT u_id FROM Tbl_User WHERE u_id = user AND u_password = pwd) THEN
SELECT '1' AS 'verfication';
ELSE
SELECT '0' AS 'verfication';
END IF;
END;
//
delimiter ; //

