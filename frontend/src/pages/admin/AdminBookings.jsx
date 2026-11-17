
import React, { useEffect, useState } from 'react';
import api from '../../services/api';

const AdminBookings = () => {
    const [bookings, setBookings] = useState([]);
    const [rejectingId, setRejectingId] = useState(null);
    const [rejectionReason, setRejectionReason] = useState('');

    const fetchBookings = () => {
        api.get('/admin/car/bookings')
            .then(response => {
                setBookings(response.data.sort((a, b) => b.id - a.id));
            })
            .catch(error => {
                console.error("Error fetching bookings:", error);
            });
    };

    useEffect(() => {
        fetchBookings();
    }, []);

    const handleApprove = async (id) => {
        try {
            await api.put(`/admin/car/booking/${id}/Approve`);
            fetchBookings();
        } catch (error) {
            console.error("Error approving booking:", error);
            alert(error.response?.data || "Could not approve booking.");
        }
    };

    const handleReject = async (id) => {
        if (!rejectionReason.trim()) {
            alert("Please enter a rejection reason.");
            return;
        }

        try {
            await api.post(
                `/admin/car/booking/${id}/reject`,
                rejectionReason.trim(),
                {
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                }
            );

            setRejectingId(null);
            setRejectionReason('');
            fetchBookings();
        } catch (error) {
            console.error("Error rejecting booking:", error);
            alert(error.response?.data || "Could not reject booking.");
        }
    };

    return (
        <div className="container fade-in" style={{ paddingTop: '2rem' }}>
            <h2>All Bookings</h2>

            <div className="bookings-list" style={{ marginTop: '2rem' }}>
                {bookings.map(booking => {
                    const status = booking.bookingStatus || booking.status || 'PENDING';
                    const isPending = status.toUpperCase() === 'PENDING';
                    const isApproved = status.toUpperCase() === 'APPROVED';
                    const isRejected = status.toUpperCase() === 'REJECTED';

                    const price = booking.totalPrice != null
                        ? booking.totalPrice
                        : booking.price;

                    const customerName = booking.userName || booking.username || 'Customer';
                    const customerEmail = booking.userEmail || booking.email || '';

                    return (
                        <div key={booking.id} className="card"
                            style={{ marginBottom: '1rem' }}>

                            <div style={{
                                display: 'flex',
                                justifyContent: 'space-between',
                                alignItems: 'flex-start',
                                flexWrap: 'wrap',
                                gap: '1rem'
                            }}>

                                <div>
                                    <h4 className="text-primary">
                                        Booking #{booking.id.toString().slice(-6)} - {booking.carBrand} {booking.carModel}
                                    </h4>

                                    <p className="text-muted">
                                        Customer: {customerName} {customerEmail && `(${customerEmail})`}
                                    </p>

                                    <div style={{ margin: '0.5rem 0' }}>
                                        <span>From: {booking.fromDate}</span>
                                        <span style={{ marginLeft: '1rem' }}>
                                            To: {booking.toDate}
                                        </span>
                                    </div>

                                    <p style={{ fontWeight: 'bold' }}>
                                        Total Price: ₹{price?.toLocaleString('en-IN')}
                                    </p>

                                    {isRejected && booking.rejectionReason && (
                                        <div style={{
                                            marginTop: '1rem',
                                            padding: '0.75rem',
                                            backgroundColor: 'rgba(244, 67, 54, 0.1)',
                                            borderRadius: '6px',
                                            color: '#F44336'
                                        }}>
                                            <strong>Rejection Reason:</strong>
                                            <p style={{ margin: '0.3rem 0 0' }}>
                                                {booking.rejectionReason}
                                            </p>
                                        </div>
                                    )}
                                </div>

                                <div style={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'flex-end',
                                    gap: '0.5rem'
                                }}>

                                    <span style={{
                                        padding: '6px 12px',
                                        borderRadius: '4px',
                                        fontWeight: '500',
                                        textTransform: 'uppercase',
                                        fontSize: '0.8rem',
                                        background: isApproved
                                            ? 'rgba(76, 175, 80, 0.1)'
                                            : isRejected
                                                ? 'rgba(244, 67, 54, 0.1)'
                                                : 'rgba(255, 152, 0, 0.1)',
                                        color: isApproved
                                            ? '#4CAF50'
                                            : isRejected
                                                ? '#F44336'
                                                : '#FF9800'
                                    }}>
                                        {status}
                                    </span>

                                    {isPending && (
                                        <div style={{
                                            display: 'flex',
                                            gap: '0.5rem',
                                            marginTop: '0.5rem'
                                        }}>
                                            <button
                                                className="btn"
                                                onClick={() => handleApprove(booking.id)}
                                                style={{
                                                    backgroundColor: '#4CAF50',
                                                    color: 'white',
                                                    border: 'none',
                                                    padding: '0.5rem 1rem'
                                                }}
                                            >
                                                Approve
                                            </button>

                                            <button
                                                className="btn"
                                                onClick={() => {
                                                    setRejectingId(booking.id);
                                                    setRejectionReason('');
                                                }}
                                                style={{
                                                    backgroundColor: '#F44336',
                                                    color: 'white',
                                                    border: 'none',
                                                    padding: '0.5rem 1rem'
                                                }}
                                            >
                                                Reject
                                            </button>
                                        </div>
                                    )}

                                    {rejectingId === booking.id && (
                                        <div style={{
                                            marginTop: '0.5rem',
                                            width: '250px'
                                        }}>
                                            <textarea
                                                value={rejectionReason}
                                                onChange={(e) => setRejectionReason(e.target.value)}
                                                placeholder="Enter rejection reason..."
                                                rows="3"
                                                style={{
                                                    width: '100%',
                                                    padding: '0.6rem',
                                                    borderRadius: '5px',
                                                    border: '1px solid #ccc',
                                                    boxSizing: 'border-box'
                                                }}
                                            />

                                            <button
                                                className="btn"
                                                onClick={() => handleReject(booking.id)}
                                                style={{
                                                    backgroundColor: '#F44336',
                                                    color: 'white',
                                                    border: 'none',
                                                    padding: '0.5rem',
                                                    marginTop: '0.5rem',
                                                    width: '100%'
                                                }}
                                            >
                                                Confirm Rejection
                                            </button>

                                            <button
                                                className="btn"
                                                onClick={() => {
                                                    setRejectingId(null);
                                                    setRejectionReason('');
                                                }}
                                                style={{ marginTop: '0.5rem', width: '100%' }}
                                            >
                                                Cancel
                                            </button>
                                        </div>
                                    )}
                                </div>
                            </div>
                        </div>
                    );
                })}

                {bookings.length === 0 && (
                    <div className="text-center text-muted" style={{ padding: '2rem' }}>
                        No bookings have been made yet.
                    </div>
                )}
            </div>
        </div>
    );
};

export default AdminBookings;