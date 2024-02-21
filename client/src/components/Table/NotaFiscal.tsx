
import { useReactTable, getCoreRowModel, flexRender } from "@tanstack/react-table"
import { Container } from "react-bootstrap"
import Mock from '../../utils/mock'
import { useEffect, useMemo, useState } from "react"

type DataNfe = {
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

  const mock = Mock()

  useEffect(() => {
    setDataNfe(mock)
  }, [])

  const data: DataNfe[] = useMemo(() => dataNfe, [dataNfe])
  const columns =  [
    {
      accessorKey: 'idedhEmi',
      header: 'Data Emissão'
    },
    {
      accessorKey: 'idenNF',
      header: 'NF'
    },
    {
      accessorKey: 'idecUF',
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
      accessorKey: 'destxNome',
      header: 'Nome Destinatario'
    },
    {
      accessorKey: 'totalICMSTotvTotTrib',
      header: 'Total Tributos'
    },
    {
      accessorKey: 'totalICMSTotvNF',
      header: 'Total NF',
    }

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
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  )
}

export default NotaFiscal