
INSERT INTO TB_DEPT VALUES (10,'ACCOUNTING','NEW YORK',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_DEPT VALUES (20,'RESEARCH','DALLAS',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_DEPT VALUES (30,'SALES','CHICAGO',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_DEPT VALUES (40,'OPERATIONS','BOSTON',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);

INSERT INTO TB_EMP VALUES (7369,'SMITH','CLERK',    7902,TO_CHAR(to_date('17-12-1980','dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),800, NULL,20, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7499,'ALLEN','SALESMAN', 7698,TO_CHAR(to_date('20-2-1981', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1600, 300,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7521,'WARD','SALESMAN',  7698,TO_CHAR(to_date('22-2-1981', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1250, 500,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7566,'JONES','MANAGER',  7839,TO_CHAR(to_date('2-4-1981',  'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),2975,NULL,20, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7654,'MARTIN','SALESMAN',7698,TO_CHAR(to_date('28-9-1981', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1250,1400,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7698,'BLAKE','MANAGER',  7839,TO_CHAR(to_date('1-5-1981',  'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),2850,NULL,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7782,'CLARK','MANAGER',  7839,TO_CHAR(to_date('9-6-1981',  'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),2450,NULL,10, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7788,'SCOTT','ANALYST',  7566,TO_CHAR(to_date('13-07-1987','dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),3000,NULL,20, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7839,'KING','PRESIDENT', NULL,TO_CHAR(to_date('17-11-1981','dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),5000,NULL,10, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7844,'TURNER','SALESMAN',7698,TO_CHAR(to_date('8-9-1981',  'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1500,   0,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7876,'ADAMS','CLERK',    7788,TO_CHAR(to_date('13-07-1987','dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1100,NULL,20, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7900,'JAMES','CLERK',    7698,TO_CHAR(to_date('3-12-1981', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),950, NULL,30, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7902,'FORD','ANALYST',   7566,TO_CHAR(to_date('3-12-1981', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),3000,NULL,20, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);
INSERT INTO TB_EMP VALUES (7934,'MILLER','CLERK',   7782,TO_CHAR(to_date('23-1-1982', 'dd-mm-yyyy'), 'YYYY-MM-DD HH24:MI:SS'),1300,NULL,10, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL);


INSERT INTO TB_FAQ VALUES (1,'제목','해결방법',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_FAQ VALUES (2,'제목2','해결방법2',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_FAQ VALUES (3,'제목3','해결방법3',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO TB_FAQ VALUES (4,'제목4','해결방법4',  TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), null);


-- TODO: 인증 관련 데이터 등록
-- 공통코드/유형 권한 등록
INSERT INTO TB_CODE_CATEGORY VALUES(300, '권한');
INSERT INTO TB_CODE VALUES(30001, 'ROLE_USER', 300, 'Y');
INSERT INTO TB_CODE VALUES(30002, 'ROLE_ADMIN', 300, 'Y');

-- admin user 1명 최초 생성
-- id : forbob
-- password : 123456
-- email : forbob@naver.com
-- roles : ROLE_ADMIN
INSERT INTO TB_USER VALUES('forbob@naver.com','$2a$10$TG1a5ywSrGNgf7/fFH.m0.EdTzHax8AGYNeAr8aIseF3DKyO0lDti','forbob','ROLE_ADMIN', 'N', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),NULL, NULL);

COMMIT;