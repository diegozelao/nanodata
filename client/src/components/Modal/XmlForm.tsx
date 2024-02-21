import { Button, Card, Form } from 'react-bootstrap';
import Modal from 'react-bootstrap/Modal';
import { useState } from 'react';


type XmlModalProps = {
  show: boolean,
  onHide: () => void,
  handleSubmit: () => void
}

function XmlModal(props: XmlModalProps) {
  
  const [files, setFiles] = useState([])
  const [arqFiles, setArqFiles] = useState([])
  const [inputFiles, setInputFiles] = useState(false)
  const [error, setError] = useState(false)
  function handleSubmit() {
    const filesXml = files
    
    console.log(files )
  }
  
  function hideFn() {
    setError(false)
    setInputFiles(false)
    return props.onHide()
  }

  function handlerChange(e: any) {
    const files = e.target.files
    const attFiles: { name: string; }[] = []

    for (let i = 0; i < files.length; i++) {
      attFiles.push(files[i].name)
      if (files[i].type !== 'text/xml') {
        setError(true)
        break
      } else {
        setError(false)
      }
    }

    if(files.length === 0) {
      setInputFiles(false)
    }else {
      setInputFiles(true)
    }
    setArqFiles(attFiles)
    setFiles(e.target.files)
  }

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Adicionar Nota Fiscal (XML)
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Card>
          <Card.Body>
            <Form.Group controlId="formFileMultiple"  className="mb-3">
              <Form.Label>Selecione os arquivos XML</Form.Label>
              <Form.Control type="file" multiple size='lg' onChange={handlerChange} />
            </Form.Group>
            {error && <p style={{color: 'red'}}>Arquivo invalido</p>}
            {inputFiles &&
              <ul>
                {arqFiles.map((file) => {
                  return <li>{file}</li>
                })}
              </ul>
            }
          </Card.Body>
        </Card>
      </Modal.Body>
      <Modal.Footer>
        {!error &&<Button variant="primary" onClick={handleSubmit}>Enviar</Button>}
        <Button variant="secondary" onClick={hideFn}>Fechar</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default XmlModal