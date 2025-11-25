const base = window.location.origin;
document.getElementById('backendUrl').textContent = base;
(() => { const overlay = document.getElementById('modal'); if (overlay) { overlay.classList.add('hidden'); overlay.style.display = 'none'; } })();
const tabs = document.querySelectorAll('.tab');
tabs.forEach(t => t.addEventListener('click', () => {
  tabs.forEach(x => x.classList.remove('active'));
  t.classList.add('active');
  document.querySelectorAll('main section').forEach(s => s.classList.add('hidden'));
  document.getElementById(t.dataset.tab).classList.remove('hidden');
  hideModal();
}));

let token = null;
const api = {
  usuarios: {
    list: () => fetch(base + '/api/usuarios', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/usuarios', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/usuarios/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/usuarios/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  },
  pacientes: {
    list: () => fetch(base + '/api/pacientes', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/pacientes', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/pacientes/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/pacientes/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  },
  doctores: {
    list: () => fetch(base + '/api/doctores', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/doctores', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/doctores/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/doctores/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  },
  tratamientos: {
    list: () => fetch(base + '/api/tratamientos', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/tratamientos', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/tratamientos/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/tratamientos/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  },
  citas: {
    list: () => fetch(base + '/api/citas', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/citas', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/citas/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/citas/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  },
  historias: {
    list: () => fetch(base + '/api/historias', { headers:{ ...(token?{Authorization:'Bearer '+token}:{}) } }).then(r => r.json()),
    create: (payload) => fetch(base + '/api/historias', {method:'POST', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    update: (id, payload) => fetch(base + '/api/historias/' + id, {method:'PUT', headers:{'Content-Type':'application/json', ...(token?{Authorization:'Bearer '+token}:{})}, body:JSON.stringify(payload)}).then(r=>r.json()),
    delete: (id) => fetch(base + '/api/historias/' + id, {method:'DELETE', headers:{...(token?{Authorization:'Bearer '+token}:{})}})
  }
};

function row(actions, ...cells) {
  const tr = document.createElement('tr');
  cells.forEach(c => { const td = document.createElement('td'); td.textContent = c ?? ''; tr.appendChild(td); });
  const tdAct = document.createElement('td');
  const edit = document.createElement('button'); edit.textContent = 'Editar';
  const del = document.createElement('button'); del.textContent = 'Eliminar'; del.className = 'ko';
  edit.addEventListener('click', actions.onEdit);
  del.addEventListener('click', actions.onDelete);
  const wrap = document.createElement('div'); wrap.className = 'actions'; wrap.appendChild(edit); wrap.appendChild(del);
  tdAct.appendChild(wrap); tr.appendChild(tdAct);
  return tr;
}

function loadUsuarios() {
  api.usuarios.list().then(list => {
    const tbody = document.querySelector('#u_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => {
          const nombre = prompt('Nombre', item.nombre);
          const email = prompt('Email', item.email);
          const rol = item.rol;
          api.usuarios.update(item.id, { id:item.id, nombre, email, rol }).then(loadUsuarios);
        },
        onDelete: () => api.usuarios.delete(item.id).then(loadUsuarios)
      }, item.id, item.nombre, item.email, item.rol));
    });
  });
}
function loadPacientes() {
  api.pacientes.list().then(list => {
    const tbody = document.querySelector('#p_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => {
          const nombre = prompt('Nombre', item.nombre);
          const email = prompt('Email', item.email);
          const telefono = prompt('Teléfono', item.telefono);
          api.pacientes.update(item.id, { id:item.id, nombre, email, telefono }).then(loadPacientes);
        },
        onDelete: () => api.pacientes.delete(item.id).then(loadPacientes)
      }, item.id, item.nombre, item.email, item.telefono));
    });
  });
}
function loadDoctores() {
  api.doctores.list().then(list => {
    const tbody = document.querySelector('#d_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => {
          const nombre = prompt('Nombre', item.nombre);
          const especialidad = prompt('Especialidad', item.especialidad);
          api.doctores.update(item.id, { id:item.id, nombre, especialidad }).then(loadDoctores);
        },
        onDelete: () => api.doctores.delete(item.id).then(loadDoctores)
      }, item.id, item.nombre, item.especialidad));
    });
  });
}
function loadTratamientos() {
  api.tratamientos.list().then(list => {
    const tbody = document.querySelector('#t_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => {
          const descripcion = prompt('Descripción', item.descripcion);
          const costo = prompt('Costo', item.costo);
          api.tratamientos.update(item.id, { id:item.id, descripcion, costo }).then(loadTratamientos);
        },
        onDelete: () => api.tratamientos.delete(item.id).then(loadTratamientos)
      }, item.id, item.descripcion, item.costo));
    });
  });
}
function loadCitas() {
  api.citas.list().then(list => {
    const tbody = document.querySelector('#c_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => editCita(item),
        onDelete: () => api.citas.delete(item.id).then(loadCitas)
      }, item.id, item.fechaHora, item.estado, item.pacienteId, item.doctorId, item.tratamientoId));
    });
  });
}
function loadHistorias() {
  api.historias.list().then(list => {
    const tbody = document.querySelector('#h_table tbody'); tbody.innerHTML = '';
    list.forEach(item => {
      tbody.appendChild(row({
        onEdit: () => editHistoria(item),
        onDelete: () => api.historias.delete(item.id).then(loadHistorias)
      }, item.id, item.pacienteId, item.medicoId, item.tratamientoId ?? '', item.diagnostico ?? '', item.fecha));
    });
  });
}

function hideModal() {
  const overlay = document.getElementById('modal');
  if (!overlay.classList.contains('hidden')) {
    overlay.classList.add('hidden');
  }
  overlay.style.display = 'none';
}
function openModal(title, builder, onSave) {
  const overlay = document.getElementById('modal'); const mTitle = document.getElementById('modal_title'); const mContent = document.getElementById('modal_content'); mTitle.textContent = title; mContent.innerHTML = ''; builder(mContent); overlay.classList.remove('hidden'); overlay.style.display = 'flex';
  const saveBtn = document.getElementById('modal_save'); const cancelBtn = document.getElementById('modal_cancel');
  const close = () => { overlay.classList.add('hidden'); saveBtn.removeEventListener('click', saveHandler); cancelBtn.removeEventListener('click', close); overlay.removeEventListener('click', outsideHandler); document.removeEventListener('keydown', escHandler); };
  const saveHandler = () => { onSave(); close(); };
  const outsideHandler = (ev) => { if (ev.target === overlay) close(); };
  const escHandler = (ev) => { if (ev.key === 'Escape') close(); };
  saveBtn.addEventListener('click', saveHandler);
  cancelBtn.addEventListener('click', close);
  overlay.addEventListener('click', outsideHandler);
  document.addEventListener('keydown', escHandler);
}

function editCita(item) {
  openModal('Editar Cita', (root) => {
    const rowA = document.createElement('div'); rowA.className='row';
    const iFecha = document.createElement('input'); iFecha.type='datetime-local'; iFecha.id='e_c_fecha'; iFecha.value=item.fechaHora||'';
    const sEstado = document.createElement('select'); sEstado.id='e_c_estado'; ['PROGRAMADA','COMPLETADA','CANCELADA'].forEach(x=>{ const o=document.createElement('option'); o.textContent=x; if (x===item.estado) o.selected=true; sEstado.appendChild(o); });
    const sPaciente = document.getElementById('c_pacienteSel').cloneNode(true); sPaciente.id='e_c_pacienteSel'; Array.from(sPaciente.options).forEach(o=>{ if (Number(o.value)===item.pacienteId) o.selected=true; });
    rowA.appendChild(iFecha); rowA.appendChild(sEstado); rowA.appendChild(sPaciente); root.appendChild(rowA);
    const rowB = document.createElement('div'); rowB.className='row-2';
    const sDoctor = document.getElementById('c_doctorSel').cloneNode(true); sDoctor.id='e_c_doctorSel'; Array.from(sDoctor.options).forEach(o=>{ if (Number(o.value)===item.doctorId) o.selected=true; });
    const sTrat = document.getElementById('c_tratamientoSel').cloneNode(true); sTrat.id='e_c_tratSel'; Array.from(sTrat.options).forEach(o=>{ if (o.value!=='' && Number(o.value)===item.tratamientoId) o.selected=true; });
    rowB.appendChild(sDoctor); rowB.appendChild(sTrat); root.appendChild(rowB);
  }, () => {
    const payload = {
      id:item.id,
      fechaHora: document.getElementById('e_c_fecha').value || item.fechaHora,
      estado: document.getElementById('e_c_estado').value,
      pacienteId: Number(document.getElementById('e_c_pacienteSel').value || item.pacienteId),
      doctorId: Number(document.getElementById('e_c_doctorSel').value || item.doctorId),
      tratamientoId: (()=>{ const v=document.getElementById('e_c_tratSel').value; return v?Number(v):null; })()
    };
    api.citas.update(item.id, payload).then(loadCitas);
  });
}

function editHistoria(item) {
  openModal('Editar Historia', (root) => {
    const rowA = document.createElement('div'); rowA.className='row';
    const sPaciente = document.getElementById('h_pacienteSel').cloneNode(true); sPaciente.id='e_h_pacienteSel'; Array.from(sPaciente.options).forEach(o=>{ if (Number(o.value)===item.pacienteId) o.selected=true; });
    const sMedico = document.getElementById('h_medicoSel').cloneNode(true); sMedico.id='e_h_medicoSel'; Array.from(sMedico.options).forEach(o=>{ if (Number(o.value)===item.medicoId) o.selected=true; });
    const sTrat = document.getElementById('h_tratamientoSel').cloneNode(true); sTrat.id='e_h_tratSel'; Array.from(sTrat.options).forEach(o=>{ if (o.value!=='' && Number(o.value)===item.tratamientoId) o.selected=true; });
    rowA.appendChild(sPaciente); rowA.appendChild(sMedico); rowA.appendChild(sTrat); root.appendChild(rowA);
    const rowB = document.createElement('div'); rowB.className='row';
    const iDet = document.createElement('input'); iDet.id='e_h_detalles'; iDet.placeholder='Detalles'; iDet.value=item.detalles||'';
    const iDiag = document.createElement('input'); iDiag.id='e_h_diagnostico'; iDiag.placeholder='Diagnóstico'; iDiag.value=item.diagnostico||'';
    const iFecha = document.createElement('input'); iFecha.id='e_h_fecha'; iFecha.type='date'; iFecha.value=item.fecha||'';
    rowB.appendChild(iDet); rowB.appendChild(iDiag); rowB.appendChild(iFecha); root.appendChild(rowB);
  }, () => {
    const payload = {
      id:item.id,
      pacienteId: Number(document.getElementById('e_h_pacienteSel').value || item.pacienteId),
      medicoId: Number(document.getElementById('e_h_medicoSel').value || item.medicoId),
      tratamientoId: (()=>{ const v=document.getElementById('e_h_tratSel').value; return v?Number(v):null; })(),
      detalles: document.getElementById('e_h_detalles').value,
      diagnostico: document.getElementById('e_h_diagnostico').value,
      fecha: document.getElementById('e_h_fecha').value
    };
    api.historias.update(item.id, payload).then(loadHistorias);
  });
}

document.getElementById('u_create').addEventListener('click', () => {
  const nombre = document.getElementById('u_nombre').value;
  const email = document.getElementById('u_email').value;
  const password = document.getElementById('u_password').value;
  const rol = 'ADMIN';
  api.usuarios.create({ nombre, email, password, rol }).then(loadUsuarios);
});
document.getElementById('auth_login').addEventListener('click', () => {
  const email = document.getElementById('auth_email').value;
  const password = document.getElementById('auth_password').value;
  fetch(base + '/api/auth/login', { method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({email, password}) })
    .then(r => r.ok ? r.json() : Promise.reject(r))
    .then(data => { token = data.token; const prefix = (token||'').slice(0,12); document.getElementById('auth_status').textContent = 'OK ' + prefix + '...'; })
    .then(() => Promise.all([api.pacientes.list(), api.doctores.list(), api.tratamientos.list()]))
    .then(([pacientes, doctores, tratamientos]) => {
      const fill = (id, items, labelFn, empty=false) => {
        const sel = document.getElementById(id); sel.innerHTML='';
        if (empty) { const o = document.createElement('option'); o.value=''; o.textContent='— ninguno —'; sel.appendChild(o); }
        items.forEach(it => { const o = document.createElement('option'); o.value=it.id; o.textContent=labelFn(it); sel.appendChild(o); });
      };
      fill('c_pacienteSel', pacientes, p => `${p.nombre} (${p.email})`);
      fill('c_doctorSel', doctores, d => `${d.nombre} · ${d.especialidad}`);
      fill('c_tratamientoSel', tratamientos, t => `${t.descripcion}`, true);
      fill('h_pacienteSel', pacientes, p => `${p.nombre} (${p.email})`);
      fill('h_medicoSel', doctores, d => `${d.nombre} · ${d.especialidad}`);
      fill('h_tratamientoSel', tratamientos, t => `${t.descripcion}`, true);
    })
    .then(() => {
      try {
        const payload = JSON.parse(atob((token||'').split('.')[1]||''));
        const rol = payload && payload.rol;
        if (rol !== 'ADMIN') {
          const tab = document.querySelector('.tab[data-tab="usuarios"]');
          const sec = document.getElementById('usuarios');
          if (tab) tab.style.display = 'none';
          if (sec) sec.classList.add('hidden');
          const firstVisibleTab = Array.from(document.querySelectorAll('.tab')).find(t => t.style.display !== 'none');
          if (firstVisibleTab) firstVisibleTab.click();
        }
      } catch(e) {}
      hideModal();
      loadUsuarios(); loadPacientes(); loadDoctores(); loadTratamientos(); loadCitas(); loadHistorias();
    })
    .catch(() => { document.getElementById('auth_status').textContent = 'ERROR'; });
});
document.getElementById('p_create').addEventListener('click', () => {
  const nombre = document.getElementById('p_nombre').value;
  const email = document.getElementById('p_email').value;
  const telefono = document.getElementById('p_telefono').value;
  api.pacientes.create({ nombre, email, telefono }).then(loadPacientes);
});
document.getElementById('d_create').addEventListener('click', () => {
  const nombre = document.getElementById('d_nombre').value;
  const especialidad = document.getElementById('d_especialidad').value;
  api.doctores.create({ nombre, especialidad }).then(() => { loadDoctores(); });
});
document.getElementById('t_create').addEventListener('click', () => {
  const descripcion = document.getElementById('t_descripcion').value;
  const costo = document.getElementById('t_costo').value;
  api.tratamientos.create({ descripcion, costo:Number(costo) }).then(() => { loadTratamientos(); });
});
document.getElementById('c_create').addEventListener('click', () => {
  const fechaRaw = document.getElementById('c_fecha').value;
  const estado = document.getElementById('c_estado').value;
  const pacienteId = Number(document.getElementById('c_pacienteSel').value);
  const doctorId = Number(document.getElementById('c_doctorSel').value);
  const tratamientoVal = document.getElementById('c_tratamientoSel').value;
  const tratamientoId = tratamientoVal ? Number(tratamientoVal) : null;
  const fechaHora = fechaRaw;
  api.citas.create({ fechaHora, estado, pacienteId, doctorId, tratamientoId }).then(loadCitas);
});
document.getElementById('h_create').addEventListener('click', () => {
  const pacienteId = Number(document.getElementById('h_pacienteSel').value);
  const medicoId = Number(document.getElementById('h_medicoSel').value);
  const tratamientoVal = document.getElementById('h_tratamientoSel').value;
  const tratamientoId = tratamientoVal ? Number(tratamientoVal) : null;
  const detalles = document.getElementById('h_detalles').value;
  const diagnostico = document.getElementById('h_diagnostico').value;
  const fecha = document.getElementById('h_fecha').value;
  api.historias.create({ pacienteId, medicoId, tratamientoId, detalles, diagnostico, fecha }).then(loadHistorias);
});
