
const hospitalRecordNo = localStorage.getItem('hospitalRecordNo');
document.getElementById('hospitalRecordNo').textContent = hospitalRecordNo;
if (!hospitalRecordNo) {
    alert('No patient selected. Please ensure a valid Hospital Record Number.');
    window.location.href = '/error.html';
}
document.getElementById('patientEnctrForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const typeOfService = document.getElementById('typeOfService').value;
    const broughtBy = document.getElementById('broughtBy').value;
    const chiefComplaint = document.getElementById('chiefComplaint').value;
    const consultationType = document.getElementById('consultationType').value;
    const consultingDoctor = document.getElementById('consultingDoctor').value;

    const patientEnctrData = {
        hospitalRecordNo: hospitalRecordNo,
        typeOfService: typeOfService,
        broughtBy: broughtBy,
        chiefComplaint: chiefComplaint,
        consultationType: consultationType,
        consultingDoctor: consultingDoctor
    };

    fetch('http://localhost:2525/api/patientenctr', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patientEnctrData)
    })
    .then(response => response.json())
    .then(data => {
        alert('Patient Encounter saved successfully!');
        window.location.href = '/success.html';
    })
    .catch(error => {
        console.error('Error saving patient encounter:', error);
        alert('There was an error saving the patient encounter.');
    });
});
