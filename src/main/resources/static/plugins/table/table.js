
function fetchPatients() {
    fetch(`http://localhost:2525/api/patients/all`)
      .then(response => response.json())
      .then(patients => {
        const patientList = document.getElementById('patientList');
        patientList.innerHTML = ''; 

        if (patients.length === 0) {
          const noPatientsMessage = document.createElement('tr');
          noPatientsMessage.innerHTML = `
              <td colspan="8" class="text-center">No patients registered</td>
          `;
          patientList.appendChild(noPatientsMessage);
        } else {

          patients.forEach(patient => {
            const row = document.createElement('tr');
            row.setAttribute('data-hospRecordNo', patient.hospitalRecordNo); 
            const patientName = `${patient.firstName} ${patient.middleName ? patient.middleName + ' ' : ''}${patient.lastName}`;

            row.innerHTML = `
              <td>${patient.hospitalRecordNo}</td>
              <td>${patientName}</td>
              <td>${patient.birthdate}</td>
              <td>${patient.sex}</td>
              <td>${patient.status}</td>
              <td>${patient.placeOfBirth}</td>
              <td>${patient.religion}</td>
              <td>${patient.presentAddress}</td>
              <td>
                <div class="dropdown">
                  <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                    <i class="bx bx-dots-vertical-rounded"></i>
                  </button>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="/editpatient?hospRecordNo=${patient.hospitalRecordNo}">
                      <i class="bx bx-edit-alt me-1"></i> Edit
                    </a>
                    <a class="dropdown-item delete-btn" data-hospRecordNo="${patient.hospitalRecordNo}" data-bs-toggle="modal" data-bs-target="#basicModaldelete">
                      <i class="bx bx-trash me-1"></i> Delete
                    </a>
                  </div>
                </div>
              </td>
            `;

            patientList.appendChild(row);
          });
        }
      })
      .catch(error => console.error('Error fetching patient data:', error));
  }

  document.addEventListener('DOMContentLoaded', function() {
    fetchPatients();
  });

  document.addEventListener('click', function(event) {

    if (event.target && event.target.classList.contains('delete-btn')) {
      const hospitalRecordNo = event.target.getAttribute('data-hospRecordNo');
      console.log("Hospital Record No from button:", hospitalRecordNo); 
      document.getElementById('hospitalRecordNo').value = hospitalRecordNo;
    }
  });
  document.getElementById('deleteBtn').addEventListener('click', function(event) {
    event.preventDefault();
    const hospitalRecordNo = document.getElementById('hospitalRecordNo').value; 
    if (hospitalRecordNo) {
      fetch(`http://localhost:2525/api/patients/softdelete/${hospitalRecordNo}`, {
        method: 'DELETE', 
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (response.ok) {
          alert('Patient Data Deleted Successfully');
          const patientRow = document.querySelector(`tr[data-hospRecordNo="${hospitalRecordNo}"]`);
          if (patientRow) {
            patientRow.remove();
          }

        window.location.href = '/patientstable';

        } else {
          alert('Failed to soft-delete patient');
        }
      })
      .catch(error => {
        console.error('Error performing soft delete:', error);
        alert('Error performing soft delete');
      });
    } else {
      alert('Hospital record number is missing');
    }

    $('#basicModaldelete').modal('hide');
  });

