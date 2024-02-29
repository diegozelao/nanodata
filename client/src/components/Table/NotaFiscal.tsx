
import { useReactTable, getCoreRowModel, flexRender } from "@tanstack/react-table"
import { Container } from "react-bootstrap"
import { useEffect, useMemo, useState } from "react"

import { IoMdDownload } from "react-icons/io";
import { IoCloudDownloadSharp } from "react-icons/io5";
import { format } from "date-fns";
import { apiInstance } from "../../services/apiInstance";

type DataNfe = {
  infNFeId: string
  idedhEmi: string
  idenNF: number
  idecUF: number
  emitCNPJ: string
  emitxFant: string
  destCNPJ: string
  destxNome: string
  totalICMSTotvTotTrib: number
  totalICMSTotvNF: string
}

function NotaFiscal() {
  const [dataNfe, setDataNfe] = useState<DataNfe[]>([])

  useEffect(() => {
    apiInstance.get('notafiscal')
    .then((response) => {
      console.log(response.data)
      setDataNfe(response.data)
    })
    
  }, [])

  function base64ToXml(base64String: string) {
    const byteString = atob(base64String);
    const buffer = new ArrayBuffer(byteString.length);
    const view = new Uint8Array(buffer);
    for (let i = 0; i < byteString.length; i++) {
      view[i] = byteString.charCodeAt(i);
    }
    const xmlString = new TextDecoder().decode(buffer);
    
    return xmlString;
  }
  function stringToFileXml(xmlString: string, filename: string) {
    const blob = new Blob([xmlString], { type: "text/xml" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = filename;
    link.click();
    URL.revokeObjectURL(url);
  }
  function handleClick(nfeId: string) {
    apiInstance.get(`xml/${nfeId}`)
    .then((response) => {
      const xmlDoc = base64ToXml(response.data.xml);
      stringToFileXml(xmlDoc, `${nfeId}.xml`)
    })
  }

  const data: DataNfe[] = useMemo(() => dataNfe, [dataNfe])
  const columns =  [
    {
      accessorKey: 'dhEmi',
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      accessorFn: (row:any) => format(new Date(row.dhEmi), "dd/MM/yyyy"),
      header: 'Data Emissão'
    },
    {
      accessorKey: 'nnf',
      header: 'NF'
    },
    {
      accessorKey: 'cuf',
      header: 'UF'
    },
    {
      accessorKey: 'emitCNPJ',
      header: 'CNPJ Emitente'
    },
    {
      accessorKey: 'emitxFant',
      header: 'Nome Emitente'
    },
    {
      accessorKey: 'destCNPJ',
      header: 'CNPJ Destinatario'
    },
    {
      accessorKey: 'destxFant',
      header: 'Nome Destinatario'
    },
    {
      accessorKey: 'vtotTrib',
      header: 'Total Tributos'
    },
    {
      accessorKey: 'vnf',
      header: 'Total NF',
    },
  ]

  const options = {
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    debugTable: true
  }

  const table = useReactTable(options)

  return (
    <Container fluid className="pt-3">
      <table className="table table-dark table-striped">
        <thead>
          {table.getHeaderGroups().map(headerGroup => (
            <tr key={headerGroup.id}>
              {headerGroup.headers.map(header => (
                <th key={header.id}>
                  {flexRender(header.column.columnDef.header, header.getContext())}
                </th>
              ))}
              <th><IoMdDownload /></th>
            </tr>
          ))}
        </thead>
        <tbody>
          {table.getRowModel().rows.map(row => (
            <tr key={row.id}>
              {row.getVisibleCells().map(cell => (
                <td key={cell.id}>
                  {flexRender(cell.column.columnDef.cell, cell.getContext())}
                </td>
              ))}
              <td><IoCloudDownloadSharp onClick={() => handleClick(row.original.infNFeId)} /></td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  )
}

export default NotaFiscal