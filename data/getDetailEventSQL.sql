select re.id, re.branch_code branchCode, c.cus_code cusCode, c.phone phone, re.pet_code, p.pet_name petName, re.doctor_code doctorCode, d.doctor_name, d.experience experience, d.majors major, re.symptom_descript symptomDescript, re.remark  remark from registration_examination re
left join pet p 
on p.pet_code = re.pet_code
left join customer c
on c.cus_code = p.cus_code
left join doctor d 
on d.doctor_code = re.doctor_code
 where re.id = 1;