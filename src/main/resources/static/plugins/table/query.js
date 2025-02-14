document.getElementById('searchButton').addEventListener('click', function () {
    const firstname = document.getElementById('firstname').value;
    const middlename = document.getElementById('middlename').value;
    const lastname = document.getElementById('lastname').value;
    const birthdate = document.getElementById('birthdate').value;
  
    let url = `http://localhost:2525/api/patients/search?`;
    if (firstname) url += `firstname=${firstname}&`;
    if (middlename) url += `middlename=${middlename}&`;
    if (lastname) url += `lastname=${lastname}&`;
    if (birthdate) url += `birthdate=${birthdate}`;
  
    url = url.endsWith('&') ? url.slice(0, -1) : url;
    url = url.endsWith('?') ? url.slice(0, -1) : url;
  
    fetch(url)
      .then(response => response.json())
      .then(data => {
        const searchResultsDiv = document.getElementById('searchResults');
        const searchInputsDiv = document.getElementById('searchInputs');
        const modalFooter = document.getElementById('modalFooter');
  
        searchInputsDiv.style.display = 'none';
        searchResultsDiv.style.display = 'block';
  
        if (data.length > 0) {
          let resultHTML = '<table class="table table-hover"><thead><tr class="table-dark"><th>Patients Name</th><th>Birthdate</th></tr></thead><tbody>';
  
          data.forEach(patient => {
            resultHTML += `
              <tr class="patient-row" data-id="${patient.id}">
                <td>${patient.firstName} ${patient.middleName} ${patient.lastName}</td>
                <td>${patient.birthdate}</td>
              </tr>
            `;
          });
  
          resultHTML += '</tbody></table>';
          searchResultsDiv.innerHTML = resultHTML;
  
          modalFooter.innerHTML = `
            <div class="btn-group">
              <a href="/register" class="btn btn-success">
                <i class="bx bx-plus bx-md"></i> Register
              </a>
              <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
                <i class="bx bx-cancel bx-md"></i> Cancel
              </button>
            </div>
          `;
  
          const patientRows = document.querySelectorAll('.patient-row');
          patientRows.forEach(row => {
            row.addEventListener('click', function () {
              const patientId = this.getAttribute('data-id');
              showPatientDetails(patientId); 
            });
          });
        } else {
          searchResultsDiv.innerHTML = '<p>No data match found</p>';
  
          modalFooter.innerHTML = `
            <div class="btn-group">
              <a href="/register" class="btn btn-success">
                <i class="bx bx-plus bx-md"></i> Register
              </a>
              <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
                <i class="bx bx-cancel bx-md"></i> Cancel
              </button>
            </div>
          `;
        }
      })
      .catch(error => {
        console.error('Error fetching search data:', error);
        alert('There was an error with the search request.');
      });
  });
  
  function showPatientDetails(patientId) {
    const url = `http://localhost:2525/api/patients/findid?id=${patientId}`;
    fetch(url)
      .then(response => response.json())
      .then(patient => {
        const modalBody = document.getElementById('modalBody');
        modalBody.innerHTML = `
          <h5>Patient Encounter Details</h5>
          <form id="patientEnctrForm">
            <div class="mb-3">
              <label for="loggedAt" class="form-label">Logged Patient Date</label>
              <input type="datetime-local" class="form-control" id="loggedAt" required>
            </div>
            <div class="mb-3">
              <label for="hospitalRecordNo" class="form-label">Hospital Record No</label>
              <input type="text" class="form-control" id="hospitalRecordNo" value="${patient.hospitalRecordNo}" readonly>
            </div>
            <div class="mb-3">
              <label for="typeOfService" class="form-label">Type of Service</label>
              <select class="form-control" id="typeOfService" required>
                <option value="">Consultation Type</option>
                <option value="MED">Medical</option>
                <option value="SUR">Surgical</option>
                <option value="OBS">Obstetrics</option>
                <option value="GYN">Gynecology</option>
                <option value="PED">Pediatrics</option>
                <option value="ORTHO">Orthopedics</option>
                <option value="OPHTHA">Ophthalmology</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="broughtBy" class="form-label">Brought By</label>
              <select class="form-control" id="broughtBy" required>
                <option value="">Brought By</option>
                <option value="Ambulance">Ambulance</option>
                <option value="Walk-in">Walk-in</option>
                <option value="Referral">Referral</option>
                <option value="FamMem">Family Member</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="consultationType" class="form-label">Consultation Type</label>
              <select class="form-control" id="consultationType" required>
                <option value="">Consulation Type</option>
                <option value="ER">Emergency</option>
                <option value="OPD">Outpatient</option>
                <option value="ADM">Inpatient</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="consultingDoctor" class="form-label">Consulting Doctor</label>
              <input type="text" class="form-control" id="consultingDoctor" required></textarea>
            </div>
            <div class="mb-3">
              <label for="chiefComplaint" class="form-label">Chief Complaint</label>
              <textarea class="form-control" id="chiefComplaint" required></textarea>
            </div>
          </form>
        `;

        const modalFooter = document.getElementById('modalFooter');
        modalFooter.innerHTML = `
          <div class="btn-group">
            <button type="button" class="btn btn-outline-success" id="saveBtnn">
              <i class="bx bx-save bx-md"></i> Save Encounter
            </button>
            <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">
              <i class="bx bx-cancel bx-md"></i> Close
            </button>
          </div>
        `;

        document.getElementById('saveBtnn').addEventListener('click', function () {
          const loggedAt = document.getElementById('loggedAt').value;
          const typeOfService = document.getElementById('typeOfService').value;
          const broughtBy = document.getElementById('broughtBy').value;
          const consultationType = document.getElementById('consultationType').value;
          const consultingDoctor = document.getElementById('consultingDoctor').value;
          const chiefComplaint = document.getElementById('chiefComplaint').value;
          const hospitalRecordNo = patient.hospitalRecordNo;

          const encounterData = {
            hospitalRecordNo: hospitalRecordNo,
            typeOfService: typeOfService,
            broughtBy: broughtBy,
            consultationType: consultationType,
            consultingDoctor: consultingDoctor,
            chiefComplaint: chiefComplaint,
            loggedAt: loggedAt,
            createdAt: new Date().toISOString(),
            updatedAt: null,
            deletedAt: null,
          };

          const saveUrl = `http://localhost:2525/api/patients/addenctr`;
          fetch(saveUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(encounterData),
          })
          .then(response => response.json())
          .then(result => {
            if (result) {
              alert('Patient encounter saved successfully!');
              window.location.href = '/patientstable';
              document.getElementById('patientEnctrForm').reset();
              const myModal = bootstrap.Modal.getInstance(document.getElementById('basicModal'));
              myModal.hide();
            } else {
              alert('Failed to save encounter.');
            }
          })
          .catch(error => {
            console.error('Error saving encounter data:', error);
            alert('There was an error saving the encounter data.');
          });
        });

        const myModal = new bootstrap.Modal(document.getElementById('basicModal'));
        myModal.show();
      })
      .catch(error => {
        console.error('Error fetching patient details:', error);
        alert('There was an error fetching the patient details.');
      });
}

  