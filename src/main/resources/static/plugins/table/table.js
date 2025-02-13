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
                  <a class="dropdown-item enctr-btn" enctr-enctrhospRecordNo="${patient.hospitalRecordNo}">
                    <i class="bx bx-user-circle me-1"></i> View Encounter
                  </a>
                  <a class="dropdown-item" href="/editpatient?hospRecordNo=${patient.hospitalRecordNo}">
                    <i class="bx bx-edit-alt me-1"></i> Edit
                  </a>
                  <a class="dropdown-item delete-btn" data-hospRecordNo="${patient.hospitalRecordNo}" data-bs-toggle="modal" data-bs-target="#basicModaldelete">
                    <i class="bx bx-trash me-1"></i> Delete
                  </a>
                  <a class="dropdown-item consent-btn" consent-conshospRecordNo="${patient.hospitalRecordNo}">
                    <i class="bx bx-printer me-1"></i> Patient Consent
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
  // Handle delete button click
  if (event.target && event.target.classList.contains('delete-btn')) {
    const hospitalRecordNo = event.target.getAttribute('data-hospRecordNo');
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

document.addEventListener('click', function (event) {
  if (event.target && event.target.classList.contains('consent-btn')) {
      const hospitalRecordNo = event.target.getAttribute('consent-conshospRecordNo');

      fetch(`http://localhost:2525/api/patients/getbyid/${hospitalRecordNo}`)
          .then(response => response.json())
          .then(patient => {
              if (patient) {
                  const patientName = `${patient.firstName} ${patient.middleName ? patient.middleName + ' ' : ''}${patient.lastName}`;
                  
                  const consentFormHTML = `
                       <div class="modal fade" id="consentModal" tabindex="-1" aria-labelledby="consentModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body" id="printArea">
                                        <!-- Logos and Header Section -->
                                        <div style="display: flex; align-items: center; justify-content: space-between; margin-bottom: 10px;">
                                            <!-- DOH Logo -->
                                            <img src="../assets/img/favicon/doh.png" alt="DOH Logo" style="height: 80px;">
                                            
                                            <!-- Centered Text -->
                                            <div style="text-align: center; flex-grow: 1;">
                                                <p style="margin: 0; font-size: 18px; font-weight: bold; color: black;">Sample District/Provincial Hospital</p>
                                                <p style="margin: 0; font-size: 18px; font-weight: bold; color: black;">Municipality of Magallanes</p>
                                                <p style="margin: 0; font-size: 14px; color: black;">Purok 8, Brgy. Sto. Rosario, Magallanes, Agusan del Norte</p>
                                            </div>

                                            <!-- BP Logo -->
                                            <img src="../assets/img/favicon/bp.png" alt="BP Logo" style="height: 80px;">
                                        </div>

                                        <!-- Form Title -->
                                        <h5 class="text-center"><b>PATIENT CONSENT FORM</b></h5>

                                        <h6 style="color: rgb(2, 146, 2);"><b>Patient Information</b></h6>
                                        <p><strong>Full Name:</strong> <span style="color: black; font-weight: bold;">${patientName}</span></p>
                                        <p><strong>Date of Birth:</strong> <span style="color: black; font-weight: bold;">${patient.birthdate}</span></p>
                                        <p><strong>Address:</strong> <span style="color: black; font-weight: bold;">${patient.presentAddress}</span></p>
                                        <p><strong>Contact Number:</strong> <span style="color: black; font-weight: bold;">${patient.contactInfo}</span></p>

                                        <h6 style="color: rgb(2, 146, 2);"><b>Consent for Medical Treatment and Data Transmission</b></h6>
                                        <p>
                                            I, <span style="color: black; font-weight: bold;">${patientName}</span>, hereby authorize 
                                            <span style="color: black; font-weight: bold;">Sample District/Provincial Hospital</span> and its authorized medical staff 
                                            to provide necessary medical examination, treatment, and healthcare services as deemed appropriate for my condition. 
                                            I understand that my medical records, including diagnosis, treatment, and laboratory results, 
                                            will be maintained by the hospital as part of my patient file.
                                        </p>

                                        <p>
                                            Additionally, I grant permission to <span style="color: black; font-weight: bold;">Sample District/Provincial Hospital</span> 
                                            to disclose and transmit my personal and medical information to relevant third parties:
                                        </p>

                                        <ul>
                                            <li><b>Health Insurance Providers</b> – For claims processing and reimbursement of medical expenses.</li>
                                            <li><b>Government Health Agencies</b> – For compliance with legal and regulatory requirements.</li>
                                            <li><b>Other Healthcare Facilities or Physicians</b> – If referral or further treatment is required.</li>
                                            <li><b>Legal Entities</b> – If requested by law enforcement or required by law.</li>
                                        </ul>

                                        <p>
                                            I understand that my data will be transmitted securely and handled in accordance with applicable data privacy laws and hospital policies. 
                                            I also acknowledge that I have the right to revoke this consent at any time by providing written notice to 
                                            <span style="color: black; font-weight: bold;">Sample District/Provincial Hospital</span>, 
                                            except when disclosure has already been made in reliance on this authorization.
                                        </p>

                                        <h6 style="color: rgb(2, 146, 2);"><b>Signature:</b></h6>
                                        <p><strong>Patient Signature:</strong> ________________________</p>
                                        <p><strong>Date:</strong> ________________________</p>

                                        <h6 style="color: rgb(2, 146, 2);"><b>For Minor Patients:</b></h6>
                                        <p><strong>Guardian/Representative Name:</strong> ________________________</p>
                                        <p><strong>Relationship to Patient:</strong> ________________________</p>
                                        <p><strong>Signature:</strong> ________________________</p>
                                        <p><strong>Date:</strong> ________________________</p>

                                        <h6 style="color: rgb(2, 146, 2);"><b>Witness (if required):</b></h6>
                                        <p><strong>Witness Name:</strong> ________________________</p>
                                        <p><strong>Date:</strong> ________________________</p>
                                    </div>
                                    <div class="modal-footer">
                                      <div class="btn-group">
                                        <button type="button" class="btn btn-outline-success" id="printConsentForm"><i class="bx bx-printer me-1"></i> Print</button>
                                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                  `;

                  // Remove existing modal if it exists
                  const existingModal = document.getElementById('consentModal');
                  if (existingModal) {
                      existingModal.remove();
                  }

                  // Append the modal to the body
                  document.body.insertAdjacentHTML('beforeend', consentFormHTML);

                  // Show the modal
                  const consentModal = new bootstrap.Modal(document.getElementById('consentModal'));
                  consentModal.show();

                  // Add print functionality
                  document.getElementById('printConsentForm').addEventListener('click', function () {
                      const printContents = document.getElementById('printArea').innerHTML;
                      const originalContents = document.body.innerHTML;

                      document.body.innerHTML = printContents;
                      window.print();
                      document.body.innerHTML = originalContents;
                      window.location.reload(); // Reload to restore the modal and page
                  });
              } else {
                  alert('Patient data not found');
              }
          })
          .catch(error => console.error('Error fetching patient details:', error));
  }
});



function formatDate(dateString) {
  const options = {
      year: 'numeric',
      month: 'short', 
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric',
      hour12: true,
  };
  const date = new Date(dateString);
  return date.toLocaleString('en-US', options).replace(',', '').replace(':00', ''); 
}

// View Encounter
document.addEventListener('click', function(event) {
if (event.target && event.target.classList.contains('enctr-btn')) {
  const hospitalRecordNo = event.target.getAttribute('enctr-enctrhospRecordNo'); 

  // Fetch encounter data
  fetch(`/api/patients/allenctrbyhospitalrecno?hospitalRecordNo=${hospitalRecordNo}`)
    .then(response => response.json())
    .then(data => {
        const container = document.querySelector('#encounter-table-container');
        
        let existingTable = document.getElementById('encounter-table');
        if (existingTable) {
            existingTable.remove();
        }

        const table = document.createElement('table');
        table.id = 'encounter-table'; 
        table.classList.add('table', 'table-hover'); 

        const thead = document.createElement('thead');
        thead.innerHTML = `
          <tr class="table-dark">
            <th style="font-size: 12px;">Encounter Date</th>
            <th style="font-size: 12px;">Hospital Record No</th>
            <th style="font-size: 12px;">Type of Service</th>
            <th style="font-size: 12px;">Brought By</th>
            <th style="font-size: 12px;">Consultation Type</th>
            <th style="font-size: 12px;">Consulting Doctor</th>
            <th style="font-size: 12px;">Chief Complaint</th>
          </tr>
        `;
        table.appendChild(thead);

        const tbody = document.createElement('tbody');
        
        if (data.length === 0) {
            const row = document.createElement('tr');
            row.innerHTML = `<td colspan="8">No encounters found for this hospital record number.</td>`;
            tbody.appendChild(row);
        } else {
            data.forEach((encounter, index) => {
                let consultationTypeClass = '';

                // Check the value of the consultationType and set the class and icon
                if (encounter.consultationType === 'ER' || encounter.consultationType === 'OPD') {
                    consultationTypeClass = 'bg-warning'; // Warning class for ER and OPD
                } else if (encounter.consultationType === 'ADM') {
                    consultationTypeClass = 'bg-success'; // Success class for ADM
                }

                const row = document.createElement('tr');
                row.innerHTML = `
                    <td style="color: black; font-size: 10.5px;">${formatDate(encounter.loggedAt)}</td> <!-- Format the date here -->
                    <td style="color: black;">${encounter.hospitalRecordNo}</td>
                    <td style="color: black;">${encounter.typeOfService}</td>
                    <td style="color: black;">${encounter.broughtBy}</td>
                    <td class="${consultationTypeClass}" style="color: white; text-align: center;">
                        ${encounter.consultationType} </td>
                    <td style="color: black;">${encounter.consultingDoctor}</td>
                    <td style="color: black;">${encounter.chiefComplaint}</td>
                `;
                tbody.appendChild(row);
            });
        }

        table.appendChild(tbody);
        container.appendChild(table);

        // Show the modal
        var encounterModal = new bootstrap.Modal(document.getElementById('encounterModal'));
        encounterModal.show();
    })
    .catch(error => {
        console.error('Error fetching encounter data:', error);
    });
}
});

