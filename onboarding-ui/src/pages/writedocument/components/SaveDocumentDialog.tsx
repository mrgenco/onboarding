import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDraft = () => {
    // api call
  };

  const handlePublish = () => {
    // api call
  };

  return (
    <React.Fragment>
      <Button variant="outlined" color='success' onClick={handleClickOpen}>
        Open form dialog
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        PaperProps={{
          component: 'form',
          onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            const formData = new FormData(event.currentTarget);
            const formJson = Object.fromEntries((formData as any).entries());
            const email = formJson.email;
            console.log(email);
            handleClose();
          },
        }}
      >
        <DialogTitle>Save Document</DialogTitle>
        <DialogContent>
          <DialogContentText>
            You can save your document as draft for later editing, or publish it online.
          </DialogContentText>
          
      <TextField
        autoFocus
        required
        margin="dense"
        id="title"
        name="title"
        label="Title"
        type="text"
        fullWidth
        variant="standard"
      />

      <TextField
        margin="dense"
        id="categories"
        name="categories"
        label="Categories"
        type="text"
        fullWidth
        variant="standard"
      />

      <TextField
        margin="dense"
        id="summary"
        name="summary"
        label="Summary"
        multiline
        rows={4}
        fullWidth
        variant="standard"
      />
        </DialogContent>
        <DialogActions>
          <Button variant="outlined" color='warning' onClick={handleDraft}>Draft</Button>
          <Button variant="outlined" color='success' onClick={handlePublish}>Publish</Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}