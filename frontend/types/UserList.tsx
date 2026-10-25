
import type { User } from './User';
import { useEffect, useState } from 'react';

export default function UserList() {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        // @ts-ignore
        async function fetchUsers() {
            try {
                const response = await fetch('/api/user');

                if (!response.ok) {
                    throw new Error('Failed to fetch users');
                }

                console.log('Response URL:', response.url);
                console.log('Content-Type:', response.headers.get('content-type'));

                const body = await response.text();
                console.log('Response body:', body);

                const data: User[] = JSON.parse(body);
                setUsers(data);
            } catch (err) {
                setError(
                    err instanceof Error
                        ? err.message
                        : 'Something went wrong!'
                );
            } finally {
                setLoading(false);
            }
        }

        fetchUsers();
    }, []);

    if (loading) {
        return <p>Loading users...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <div>
            <h1>Users</h1>

            {users.map((user) => (
                <div key={user.internalUserId}>
                    <h2>
                        {user.firstName} {user.lastName}
                    </h2>

                    <p>Username: {user.userName}</p>
                    <p>Email: {user.email}</p>
                    <p>Phone: {user.phoneNumber}</p>
                    <p>External ID: {user.externalUserId}</p>
                </div>
            ))}
        </div>
    );
}
