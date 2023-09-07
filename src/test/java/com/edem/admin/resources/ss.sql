

COPY public.courses (course_id, course_description, course_duration, course_name, instructor_id) FROM stdin;
1	Java for beginners	3 months	Java	1
2	C++ for beginners	3 months	C++	2
\.

COPY public.enrolled_in (course_id, student_id) FROM stdin;
1	1
2	2
\.


COPY public.instructors (instructor_id, first_name, last_name, summary, user_id) FROM stdin;
1	instructor1FN	instructor2LN	Experienced Java	3
2	instructor2FN	instructor2LN	Senior Instructor	4
\.



COPY public.roles (role_id, name) FROM stdin;
1	Admin
2	Instructor
3	Student
\.


COPY public.students (student_id, first_name, last_name, level, user_id) FROM stdin;
1	student1FN	student2LN	beginner	5
2	student2FN	student2LN	intermediate	6
\.


COPY public.user_role (user_id, role_id) FROM stdin;
1	1
1	2
2	3
3	2
4	2
5	3
6	3
\.


COPY public.users (user_id, email, password) FROM stdin;
1	user1@gmail.com	pass1
2	user2@gmail.com	pass2
3	instructor1@gmail.com	pass1
4	instructor2@gmail.com	pass2
5	std1@gmail.com	pass1
6	std2@gmail.com	pass2
\.


