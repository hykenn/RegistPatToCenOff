
document.getElementById('sameAddressCheckbox').addEventListener('change', function() {
  var presentAddress = document.getElementById('presentAddress').value;
  var presentZipCode = document.getElementById('presentZipCode').value;

  if (this.checked) {

    document.getElementById('permanentAddress').value = presentAddress;
    document.getElementById('permanentZipCode').value = presentZipCode;

    document.getElementById('permanentAddress').disabled = true;
    document.getElementById('permanentZipCode').disabled = true;
  } else {

    document.getElementById('permanentAddress').disabled = false;
    document.getElementById('permanentZipCode').disabled = false;
  }
});

document.getElementById('generateNewHospitalRecordNo').addEventListener('click', async function() {
try {
const response = await fetch('/api/patients/generate-hospital-record-no');
if (response.ok) {
const data = await response.text();
document.getElementById('hospitalRecordNo').value = data;
} else {
console.error('Failed to generate hospital record number');
}
} catch (error) {
console.error('Error generating hospital record number:', error);
}
});

document.addEventListener('DOMContentLoaded', async function() {
try {
const response = await fetch('/api/patients/generate-hospital-record-no');
if (response.ok) {
const data = await response.text();
document.getElementById('hospitalRecordNo').value = data;
} else {
console.error('Failed to generate hospital record number');
}
} catch (error) {
console.error('Error generating hospital record number:', error);
}
});

document.getElementById('patientForm').addEventListener('submit', async function(event) {
  event.preventDefault();

  const formData = new FormData(this);
  const patientData = Object.fromEntries(formData.entries());


  const hospitalRecordNo = document.getElementById('hospitalRecordNo').value;
  patientData.hospitalRecordNo = hospitalRecordNo; 

  try {
    const response = await fetch('/api/patients/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(patientData),
    });

    if (response.ok) {
      const savedPatient = await response.json();
      alert('Patient Registered Successfully');
      
      window.location.href = '/patientstable';
    } else {
      alert('Error registering patient');
    }
  } catch (error) {
    alert('An error occurred during registration');
    console.error('Error submitting form:', error);
  }
});
