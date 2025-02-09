
function getPatientData() {
    const urlParams = new URLSearchParams(window.location.search);
    const hospitalRecordNo = urlParams.get('hospRecordNo'); 

    if (hospitalRecordNo) {
        fetch(`http://localhost:2525/api/patients/getbyid/${hospitalRecordNo}`)
            .then(response => response.json())
            .then(patient => {

                document.getElementById('hospitalRecordNo').value = patient.hospitalRecordNo;
                document.getElementById('firstName').value = patient.firstName;
                document.getElementById('middleName').value = patient.middleName || '';
                document.getElementById('lastName').value = patient.lastName;
                document.getElementById('sex').value = patient.sex;
                document.getElementById('status').value = patient.status;
                document.getElementById('phoneNumber').value = patient.contactInfo || '';
                document.getElementById('birthdate').value = patient.birthdate;
                document.getElementById('religion').value = patient.religion;
                document.getElementById('birthPlace').value = patient.placeOfBirth;
                document.getElementById('presentAddress').value = patient.presentAddress;
                document.getElementById('presentZipCode').value = patient.presentZipCode || '';
                document.getElementById('permanentAddress').value = patient.permanentAddress || '';
                document.getElementById('permanentZipCode').value = patient.permanentZipCode || '';
            })
            .catch(error => console.error('Error fetching patient data:', error));
    } else {
        console.error('No hospitalRecordNo provided in URL');
    }
}

document.addEventListener('DOMContentLoaded', getPatientData);

document.getElementById('patientForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    const hospitalRecordNo = document.getElementById('hospitalRecordNo').value;

    const patientData = {
        hospitalRecordNo: hospitalRecordNo,
        firstName: document.getElementById('firstName').value,
        middleName: document.getElementById('middleName').value,
        lastName: document.getElementById('lastName').value,
        sex: document.getElementById('sex').value,
        status: document.getElementById('status').value,
        contactInfo: document.getElementById('phoneNumber').value,
        birthdate: document.getElementById('birthdate').value,
        religion: document.getElementById('religion').value,
        placeOfBirth: document.getElementById('birthPlace').value,
        presentAddress: document.getElementById('presentAddress').value,
        presentZipCode: document.getElementById('presentZipCode').value,
        permanentAddress: document.getElementById('permanentAddress').value,
        permanentZipCode: document.getElementById('permanentZipCode').value
    };

    fetch(`http://localhost:2525/api/patients/updatebyhospitalRecordNo/${hospitalRecordNo}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patientData) 
    })
    .then(response => {
        if (response.ok) {
            alert('Patient updated successfully');
            window.location.href = '/patientstable'; 
        } else {
            alert('Failed to update patient');
        }
    })
    .catch(error => {
        console.error('Error updating patient:', error);
        alert('Error updating patient');
    });
});