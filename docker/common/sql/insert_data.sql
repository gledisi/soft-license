INSERT INTO public.role(id, name, code)
VALUES (1, 'EMPLOYEE', 'EMP');

INSERT INTO public.users(id, first_name, last_name, username, email, password, created_by, created_on, role_id, deleted)
VALUES (1,'mario','super','perfect','mario@gmail.com','$2a$10$QATmzysnV7H6MnOFkXwcgOb9j.u5qnG3giUrQzMSQ41WJexdpBOIu',1,'2022-07-20 19:42:30.824+01',1,false);

INSERT INTO public.licenses(id, organization_id, license_type, product_name, max_number, allocated)
VALUES (1, 1, 'trial', 'zoom', 20, 50);

INSERT INTO public.organizations(id, name, contact_name, contact_email, contact_phone)
VALUES (1, 'city', 'Mario Gegaj', 'mario.gegaj@city.ac.uk', '0744902232');