import React, { useState } from 'react';

function Prueba() {
  const [people, setPeople] = useState([{ firstName: '', lastName: '' }]);

  const handleAddInput = () => {
    setPeople([...people, { firstName: '', lastName: '' }]);
  };

  const handleNameChange = (index, field, value) => {
    const newPeople = [...people];
    newPeople[index][field] = value;
    setPeople(newPeople);
  };

  return (
    <div>
      {people.map((person, index) => (
        <div key={index}>
          <input
            type="text"
            value={person.firstName}
            onChange={(e) => handleNameChange(index, 'firstName', e.target.value)}
            placeholder="First Name"
          />
          <input
            type="text"
            value={person.lastName}
            onChange={(e) => handleNameChange(index, 'lastName', e.target.value)}
            placeholder="Last Name"
          />
        </div>
      ))}

      <button onClick={handleAddInput}>Add Another Person</button>

      <pre>{JSON.stringify(people, null, 2)}</pre>
    </div>
  );
}

export default Prueba;
