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
  
    // Remove trailing '&' or '?' if no valid params
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
          <h5>Patient Details</h5>
          <p class="patient-details"><strong>Hospital Record No:</strong> <h5>${patient.hospitalRecordNo}</h5></p>
          <p class="patient-details"><strong>Name:</strong> <h5>${patient.firstName} ${patient.middleName} ${patient.lastName}</h5></p>
          <p class="patient-details"><strong>Birthdate:</strong> <h5>${patient.birthdate}</h5></p>
          <p class="patient-details"><strong>Sex:</strong> <h5>${patient.sex}</h5></p>
          <p class="patient-details"><strong>Religion:</strong> <h5>${patient.religion}</h5></p>
          <p class="patient-details"><strong>Contact:</strong> <h5>${patient.contactInfo}</h5></p>
          <p class="patient-details"><strong>Birth Place:</strong> <h5>${patient.placeOfBirth}</h5></p>
          <p class="patient-details"><strong>Address:</strong> <h5>${patient.presentAddress}</h5></p>
        `;
  
        const modalFooter = document.getElementById('modalFooter');
        modalFooter.innerHTML = `
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal">
            <i class="bx bx-cancel bx-md"></i> Close
          </button>
        `;
  
        const myModal = new bootstrap.Modal(document.getElementById('basicModal'));
        myModal.show();
      })
      .catch(error => {
        console.error('Error fetching patient details:', error);
        alert('There was an error fetching the patient details.');
      });
  }
  