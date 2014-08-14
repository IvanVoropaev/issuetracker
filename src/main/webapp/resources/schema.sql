
CREATE TABLE comments(
    comment_id        INT         auto_increment NOT NULL,
    user_id           INT         NOT NULL,
    issue_id          INT         NOT NULL,
    comment_date      DATETIME    NOT NULL,
    comment_status    TEXT        NOT NULL,
    comment_text      TEXT        NOT NULL,
    PRIMARY KEY (comment_id, issue_id)
)
;

CREATE TABLE issue(
    issue_id             INT         auto_increment NOT NULL,
    user_id              INT         NOT NULL,
    issue_date           DATETIME    NOT NULL,
    issue_name           TEXT        NOT NULL,
    issue_description    TEXT        NOT NULL,
    issue_status         TEXT        NOT NULL,
    PRIMARY KEY (issue_id)
)
;

CREATE TABLE users(
    user_id       INT     auto_increment NOT NULL,
    user_name     TEXT    NOT NULL,
    user_email    TEXT    NOT NULL,
    password      TEXT    NOT NULL,
    PRIMARY KEY (user_id)
)
;

-- 
-- TABLE: comments 
--

ALTER TABLE comments ADD CONSTRAINT Refusers91 
    FOREIGN KEY (user_id)
    REFERENCES users(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE comments ADD CONSTRAINT Refissue121 
    FOREIGN KEY (issue_id)
    REFERENCES issue(issue_id) ON DELETE RESTRICT ON UPDATE RESTRICT
;


-- 
-- TABLE: issue 
--

ALTER TABLE issue ADD CONSTRAINT Refusers111 
    FOREIGN KEY (user_id)
    REFERENCES users(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT


