INSERT INTO users (user_name,user_email,password)
VALUES ('username','mail@mail.com','password');
INSERT INTO issue (user_id,issue_date,issue_name,issue_description,issue_status)
VALUES ('1',{TS '2014-09-05 15:14:30'},'issuename','issuedescription','issuestatus');
INSERT INTO comments (user_id,issue_id,comment_date,comment_status,comment_text)
VALUES ('1','1',{TS '2014-09-05 15:14:30'},'commentstatus','commenttext');