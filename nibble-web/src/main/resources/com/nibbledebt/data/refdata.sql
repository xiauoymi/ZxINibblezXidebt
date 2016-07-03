insert into account_type (id, code, description, createdTs, createdUser) values (1, 'loan', 'accounts to apply nibble funds to', now(), 'admin');
insert into account_type (id, code, description, createdTs, createdUser) values (2, 'funding', 'accounts to apply nibble funds from', now(), 'admin');

INSERT INTO public.nibbler_account(
            nibbler_account_id, created_ts, created_user, updated_ts, updated_user, 
            cumulative_roundups_amount, external_id, last_transaction_pull, 
            name, "number", number_mask, user_for_rounding, user_for_payoff, 
            account_type_id, institution_id, nibbler_id, fundingsourceid, 
            dwollaloanid, lastcheckingbalance, lastpaymentfee, balancebelow20)
    VALUES  (-1,'2016-06-19 17:59:43.544','flo471764@clipmail.eu','2016-06-19 17:59:43.544','2016-06-19 17:59:43.544',5000.00,'9190728','2016-06-18 17:59:43.544','creditCard - 4100007777','4100007777','4100007777',true,false,401,235,(select nibbler_nibbler_id from nibbler_directory  where username='admin@nibbledebt.com'),'https://api-uat.dwolla.com/funding-sources/3f715bae-d7c7-43d8-be65-ecb16d288417','','2016-06-19 17:59:43.544','2016-06-19 17:59:43.544',0)
