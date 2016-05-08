-- Users
INSERT INTO User (id, username, role, `password`) VALUES (1, 'student', 'STUDENT', X'243261243036246e74756a706f424d6b505a47355373537758314a39652e6b314c47745a32386a5131496644785644575944366264615a3464355971');
INSERT INTO User (id, username, role, `password`) VALUES (2, 'teacher', 'TEACHER', X'243261243036244242524256617178486c456c623331343333612e664f4f4f633945725a37315934586750422f33527954543136645a70774945394b');
-- user=student pw=asd user=teacher pw=asdasd

-- Authentication
INSERT INTO Authentication (id, token, `user`) VALUES (1, 'asd', 1);
INSERT INTO Authentication (id, token, `user`) VALUES (2, 'qwe', 2);