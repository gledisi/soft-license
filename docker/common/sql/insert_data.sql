INSERT INTO public.role(id, name, code)
VALUES (1, 'EMPLOYEE', 'EMP');

INSERT INTO public.users(id, first_name, last_name, username, email, password, created_by, created_on, role_id, deleted)
VALUES (1,'mario','super','perfect','mario@gmail.com','$2a$10$QATmzysnV7H6MnOFkXwcgOb9j.u5qnG3giUrQzMSQ41WJexdpBOIu',1,'2022-07-20 19:42:30.824+01',1,false);

INSERT INTO public.organizations(id, name, contact_name, contact_email, contact_phone)
VALUES (1, 'city', 'Mario Gegaj', 'mario.gegaj@city.ac.uk', '0744902232');
INSERT INTO public.organizations(id, name, contact_name, contact_email, contact_phone)
VALUES (2, 'Amazon', 'Mario G.', 'mario.gegaj@city.ac.uk', '0744901234');

INSERT INTO public.software(id, product_name, description, logo)
VALUES (1, 'Zoom', 'Zoom is a cloud-based video conferencing platform that can be used for video conferencing meetings, audio conferencing, webinars, meeting recordings, and live chat.', null);
INSERT INTO public.software(id, product_name, description, logo)
VALUES (2, 'JetBrains', 'JetBrains is a software development company which makes tools for software developers and project managers', null);



--license type
INSERT INTO public.license_type(id, name, software_id)
VALUES (1, 'ZOOM ONE PRO', 1);
INSERT INTO public.license_type(id, name, software_id)
VALUES (2, 'ZOOM ONE BUSINESS', 1);
INSERT INTO public.license_type(id, name, software_id)
VALUES (3, 'ZOOM ONE PLUS', 1);
INSERT INTO public.license_type(id, name, software_id)
VALUES (3, 'ZOOM ONE ENTERPRISE', 1);
INSERT INTO public.license_type(id, name, software_id)
VALUES (4, 'JetBrains IntelliJ IDEA Ultimate', 2);
INSERT INTO public.license_type(id, name, software_id)
VALUES (5, 'JetBrains All Products Pack', 2);
INSERT INTO public.license_type(id, name, software_id)
VALUES (6, 'JetBrains dotUltimate', 2);

-- License status
--RECEIVED,PURCHASED,ACTIVE,RETIRED, IN_STOCK
INSERT INTO public.license_status(id, name)
VALUES (1, 'RECEIVED');
INSERT INTO public.license_status(id, name)
VALUES (2, 'PURCHASED');
INSERT INTO public.license_status(id, name)
VALUES (3, 'ACTIVE');
INSERT INTO public.license_status(id, name)
VALUES (4, 'RETIRED');
INSERT INTO public.license_status(id, name)
VALUES (5, 'IN_STOCK');

-- License allocation status
-- Allocated; Awaiting Inventory; Permanent; Unallocated.
INSERT INTO public.license_allocation_status(id, name)
VALUES (1, 'Allocated');
INSERT INTO public.license_allocation_status(id, name)
VALUES (2, 'Awaiting Inventory');
INSERT INTO public.license_allocation_status(id, name)
VALUES (3, 'Permanent');
INSERT INTO public.license_allocation_status(id, name)
VALUES (4, 'Unallocated');

-- Employee status
-- Active; Inactive; Retired; On_Leave; Pending
INSERT INTO public.employee_status(id, name)
VALUES (1, 'Active');
INSERT INTO public.employee_status(id, name)
VALUES (2, 'Inactive');
INSERT INTO public.employee_status(id, name)
VALUES (3, 'Retired');
INSERT INTO public.employee_status(id, name)
VALUES (4, 'On_Leave');
INSERT INTO public.employee_status(id, name)
VALUES (5, 'Pending');

-- License request Status
-- Pending; Canceled; Approved;
INSERT INTO public.license_request_status(id, name)
VALUES (1, 'Pending');
INSERT INTO public.license_request_status(id, name)
VALUES (2, 'Canceled');
INSERT INTO public.license_request_status(id, name)
VALUES (3, 'Approved');

INSERT INTO public.licenses(id, organization_id, license_type_id,status_id, max_number, allocated, purchased_date,expiry_date, annual_cost_per_license)
VALUES (1, 1, 2,3, 20, 10, '2021-09-28', '2022-10-30', 1280);
INSERT INTO public.licenses(id, organization_id, license_type_id,status_id, max_number, allocated, purchased_date,expiry_date, annual_cost_per_license)
VALUES (2, 1, 4,4, 10, 8, '2021-09-28', '2022-10-30', 1800);
INSERT INTO public.licenses(id, organization_id, license_type_id,status_id, max_number, allocated, purchased_date,expiry_date, annual_cost_per_license)
VALUES (3, 1, 6,3, 5, 3, '2021-09-28', '2022-10-30', 620);