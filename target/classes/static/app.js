// Tab Logic
document.querySelectorAll('.tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
        document.querySelectorAll('.visualizer-container').forEach(s => s.style.display = 'none');
        
        btn.classList.add('active');
        document.getElementById(btn.dataset.target).style.display = 'block';
    });
});

// Helper for Status Messages
function showStatus(id, msg, type = 'success') {
    const el = document.getElementById(id);
    el.textContent = msg;
    el.className = 'status-box ' + (type === 'error' ? 'status-error' : 'status-success');
    setTimeout(() => { el.textContent = ''; el.className = 'status-box'; }, 3000);
}

// --- Stack Functions ---
async function initStack() {
    const size = document.getElementById('stack-size').value;
    if (!size) return showStatus('stack-status', 'Enter a size', 'error');
    if (size > 20) return showStatus('stack-status', 'Max size is 20 for visualization', 'error');
    
    try {
        const res = await fetch(`/api/stack/init/${size}`, { method: 'POST' });
        if (res.ok) {
            document.getElementById('stack-ops').style.display = 'flex';
            updateStackVisual();
            showStatus('stack-status', 'Stack initialized');
        }
    } catch (e) { showStatus('stack-status', 'Error initializing', 'error'); }
}

async function updateStackVisual() {
    const res = await fetch('/api/stack/state');
    const state = await res.json();
    const visual = document.getElementById('stack-visual');
    visual.innerHTML = '';

    for (let i = 0; i < state.capacity; i++) {
        const cell = document.createElement('div');
        if (i < state.elements.length) {
            cell.className = 'cell';
            cell.innerHTML = `<span class="cell-text">${state.elements[i]}</span>`;
            if (i === state.top) {
                cell.innerHTML += '<span class="pointer">← Top</span>';
            }
        } else {
            cell.className = 'cell-empty';
        }
        visual.appendChild(cell);
    }
}

async function pushStack() {
    const val = document.getElementById('stack-input').value;
    if (!val) return;
    if (val.length > 6) return showStatus('stack-status', 'Max 6 digits allowed', 'error');
    try {
        const res = await fetch(`/api/stack/push/${val}`, { method: 'POST' });
        const data = await res.json();
        if (res.ok) {
            updateStackVisual();
            document.getElementById('stack-input').value = '';
        } else {
            showStatus('stack-status', data.message || 'Overflow!', 'error');
        }
    } catch (e) { showStatus('stack-status', 'Overflow!', 'error'); }
}

async function popStack() {
    try {
        const res = await fetch('/api/stack/pop', { method: 'DELETE' });
        const data = await res.json();
        if (res.ok) {
            updateStackVisual();
            showStatus('stack-status', `Popped: ${data.popped}`);
        } else {
            showStatus('stack-status', 'Underflow!', 'error');
        }
    } catch (e) { showStatus('stack-status', 'Underflow!', 'error'); }
}

async function peekStack() {
    try {
        const res = await fetch('/api/stack/peek');
        const data = await res.json();
        if (res.ok) {
            showStatus('stack-status', `Peek: ${data.peeked}`);
        } else {
            showStatus('stack-status', 'Empty!', 'error');
        }
    } catch (e) { showStatus('stack-status', 'Empty!', 'error'); }
}

// --- Queue Functions ---
async function initQueue() {
    const size = document.getElementById('queue-size').value;
    if (!size) return showStatus('queue-status', 'Enter a size', 'error');
    if (size > 20) return showStatus('queue-status', 'Max size is 20 for visualization', 'error');
    
    try {
        const res = await fetch(`/api/queue/init/${size}`, { method: 'POST' });
        if (res.ok) {
            document.getElementById('queue-ops').style.display = 'flex';
            updateQueueVisual();
            showStatus('queue-status', 'Queue initialized');
        }
    } catch (e) { showStatus('queue-status', 'Error initializing', 'error'); }
}

async function updateQueueVisual() {
    const res = await fetch('/api/queue/state');
    const state = await res.json();
    const visual = document.getElementById('queue-visual');
    visual.innerHTML = '';

    for (let i = 0; i < state.capacity; i++) {
        const cell = document.createElement('div');
        const isOccupied = isIndexOccupied(i, state.front, state.rear, state.size, state.capacity);
        
        if (isOccupied) {
            cell.className = 'cell';
            cell.innerHTML = `<span class="cell-text">${state.rawArray[i]}</span>`;
        } else {
            cell.className = 'cell-empty';
        }

        let pointers = [];
        if (i === state.front && state.size > 0) pointers.push('Front');
        if (i === state.rear && state.size > 0) pointers.push('Rear');
        
        if (pointers.length > 0) {
            cell.innerHTML += `<span class="pointer">↑ ${pointers.join(' & ')}</span>`;
        }
        
        visual.appendChild(cell);
    }
}

function isIndexOccupied(i, front, rear, size, capacity) {
    if (size === 0) return false;
    if (front <= rear) {
        return i >= front && i <= rear;
    } else {
        return i >= front || i <= rear;
    }
}

async function enqueueQueue() {
    const val = document.getElementById('queue-input').value;
    if (!val) return;
    if (val.length > 6) return showStatus('queue-status', 'Max 6 digits allowed', 'error');
    try {
        const res = await fetch(`/api/queue/enqueue/${val}`, { method: 'POST' });
        const data = await res.json();
        if (res.ok) {
            updateQueueVisual();
            document.getElementById('queue-input').value = '';
        } else {
            showStatus('queue-status', 'Queue Full!', 'error');
        }
    } catch (e) { showStatus('queue-status', 'Queue Full!', 'error'); }
}

async function dequeueQueue() {
    try {
        const res = await fetch('/api/queue/dequeue', { method: 'DELETE' });
        const data = await res.json();
        if (res.ok) {
            updateQueueVisual();
            showStatus('queue-status', `Dequeued: ${data.dequeued}`);
        } else {
            showStatus('queue-status', 'Queue Empty!', 'error');
        }
    } catch (e) { showStatus('queue-status', 'Queue Empty!', 'error'); }
}

async function peekQueue() {
    try {
        const res = await fetch('/api/queue/peek');
        const data = await res.json();
        if (res.ok) {
            showStatus('queue-status', `Peek: ${data.peeked}`);
        } else {
            showStatus('queue-status', 'Queue Empty!', 'error');
        }
    } catch (e) { showStatus('queue-status', 'Queue Empty!', 'error'); }
}
