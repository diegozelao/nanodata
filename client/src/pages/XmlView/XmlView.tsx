import { useState } from 'react';
import NotaFiscal from '../../components/Table/NotaFiscal'

//Delete this after api
import { Button, Col, Container, Row } from 'react-bootstrap'
import XmlModal from '../../components/Modal/XmlForm';

export default function XmlView() {
  
  const [modalShow, setModalShow] = useState(false);
  return (
    <>
      <Container fluid>
        <Row>
          <Col className='d-flex justify-content-start'>
            <h1>{'Relatorio >'}</h1>
          </Col>
        </Row>
        <hr />
        <Row className='d-flex justify-content-beetween'>
          <Col><h2>Nota Fiscal</h2></Col>
          <Col>
            <Button 
              onClick={() => setModalShow(true)}
              variant="primary" 
              size='lg'>Adicionar Nota Fiscal (XML)
            </Button>
          </Col>
        </Row>
        <NotaFiscal />
        <XmlModal 
          show={modalShow}
          onHide={() => setModalShow(false)}
          handleSubmit={() => setModalShow(false)}
        />
      </Container>
    </>
  )
}